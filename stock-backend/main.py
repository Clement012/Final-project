from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from yahoo_crumb import YahooConnector
import requests
import logging
import os
from typing import List, Optional
from pydantic import BaseModel

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger("YahooStock")

app = FastAPI(title="Yahoo Stock API")

# Enable CORS for local development
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173", "http://127.0.0.1:5173"],  # Vue.js dev server
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

class StockData(BaseModel):
    symbol: str
    data: dict

class YahooStock:
    def __init__(self):
        self._initialize_with_retry()
    
    def _initialize_with_retry(self, max_retries=3):
        for attempt in range(max_retries):
            try:
                YahooConnector.reset_cookie_crumb()
                self.yahoo_crumb = YahooConnector.get_crumb()
                
                if self.yahoo_crumb:
                    return
            except Exception as e:
                logger.error(f"Error initializing Yahoo connector: {str(e)}")
        
        self.yahoo_crumb = ""
    
    def get_stock_data(self, symbol, max_retries=3):
        if not self.yahoo_crumb:
            return None
            
        for attempt in range(max_retries):
            try:
                url = f'https://query1.finance.yahoo.com/v7/finance/quote?symbols={symbol}&crumb={self.yahoo_crumb}'
                
                headers = {
                    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36",
                    "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
                    "Accept-Language": "en-US,en;q=0.5",
                    "Cookie": YahooConnector.get_cookie() or ""
                }
                
                response = requests.get(url, headers=headers)
                
                if response.status_code == 200:
                    return response.json()
            except Exception as e:
                logger.error(f"Error fetching stock data: {str(e)}")
        
        return None

# Initialize the stock API at startup
stock_api = YahooStock()

def read_symbols_from_file(filename):
    symbols = []
    try:
        if os.path.exists(filename):
            with open(filename, 'r') as file:
                for line in file:
                    symbol = line.strip()
                    if symbol:  # Skip empty lines
                        symbols.append(symbol)
    except Exception as e:
        logger.error(f"Error reading symbols file: {str(e)}")
    
    return symbols

@app.get("/")
def read_root():
    return {"status": "Yahoo Stock API is running"}

@app.get("/api/stocks/{symbol}", response_model=Optional[StockData])
def get_stock(symbol: str):
    result = stock_api.get_stock_data(symbol)
    if not result:
        raise HTTPException(status_code=404, detail=f"Stock data for {symbol} not found")
    return {"symbol": symbol, "data": result}

@app.get("/api/stocks", response_model=List[StockData])
def get_multiple_stocks(symbols: str = ""):
    if symbols:
        symbol_list = symbols.split(",")
    else:
        symbol_list = read_symbols_from_file('hkstock.txt')
        if not symbol_list:
            symbol_list = ["0005.HK", "0700.HK"]  # Default symbols
    
    results = []
    for symbol in symbol_list:
        data = stock_api.get_stock_data(symbol)
        if data:
            results.append({"symbol": symbol, "data": data})
    
    return results

if __name__ == "__main__":
    import uvicorn
    uvicorn.run("main:app", host="0.0.0.0", port=8000, reload=True)