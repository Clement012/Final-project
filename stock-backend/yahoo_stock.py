# from yahoo_crumb import YahooConnector
# import requests
# import logging
# import os

# logging.basicConfig(level=logging.INFO)
# logger = logging.getLogger("YahooStock")

# class YahooStock:
#     def __init__(self):
#         self._initialize_with_retry()
    
#     def _initialize_with_retry(self, max_retries=3):
#         for attempt in range(max_retries):
#             try:
#                 YahooConnector.reset_cookie_crumb()
#                 self.yahoo_crumb = YahooConnector.get_crumb()
                
#                 if self.yahoo_crumb:
#                     return
#             except Exception as e:
#                 pass
        
#         self.yahoo_crumb = ""
    
#     def get_stock_data(self, symbol, max_retries=3):
#         if not self.yahoo_crumb:
#             return None
            
#         for attempt in range(max_retries):
#             try:
#                 url = f'https://query1.finance.yahoo.com/v7/finance/quote?symbols={symbol}&crumb={self.yahoo_crumb}'
                
#                 headers = {
#                     "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36",
#                     "Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
#                     "Accept-Language": "en-US,en;q=0.5",
#                     "Cookie": YahooConnector.get_cookie() or ""
#                 }
                
#                 response = requests.get(url, headers=headers)
                
#                 if response.status_code == 200:
#                     return response.json()
#             except Exception:
#                 pass
        
#         return None

# def read_symbols_from_file(filename):
#     symbols = []
#     try:
#         if os.path.exists(filename):
#             with open(filename, 'r') as file:
#                 for line in file:
#                     symbol = line.strip()
#                     if symbol:  # Skip empty lines
#                         symbols.append(symbol)
#     except Exception as e:
#         pass
    
#     return symbols

# if __name__ == "__main__":
#     symbols = read_symbols_from_file('hkstock.txt')
    
#     if not symbols:
#         symbols = ["0005.HK", "0700.HK"]  # Default symbols if file reading fails
    
#     stock_api = YahooStock()
    
#     if stock_api.yahoo_crumb:
#         for symbol in symbols:
#             result = stock_api.get_stock_data(symbol)
#             if result:
#                 print(f"Successfully retrieved data: {result}")