function fetchStockData(symbol) {
  return fetch(`/fiveminute/${symbol}`)
    .then(response => response.json());
}

function fetchMAData(symbol) {
  return fetch(`/ma/${symbol}`)
    .then(response => response.json());
}

function updateChart(symbol) {
  Promise.all([fetchStockData(symbol), fetchMAData(symbol)]).then(([five, ma]) => {
    
    const fiveMin = five.map(item => [new Date(item.time).getTime(), item.regularMarketPrice]);
    const mA = ma.map(item => [new Date(item.time).getTime(), item.averagePrice]);

    Highcharts.stockChart('stockChart', {
      rangeSelector: {
        selected: 1
      },
      title: {
        text: `${symbol} 5-Minute Stock Price`
      },
      time: {
        timezone: 'Asia/Hong_Kong'
      },
      series: [
        {
          name: 'Price',
          data: fiveMin,
          tooltip: {
            valueDecimals: 2
          }
        },
        {
          name: 'Moving Average',
          data: mA,
          tooltip: {
            valueDecimals: 2
          }
        }
      ]
    });
  }).catch(error => {
    console.error('Error fetching data:', error);
  });
}

function updateBidAsk(symbol) {
  fetch(`/bidask/${symbol}`)
    .then(response => response.json())
    .then(data => {
      if (data.length > 0) {
        const bidCell = document.querySelector('.bid');
        const askCell = document.querySelector('.ask');
        const linkCell = document.querySelector('.Link a');
        bidCell.textContent = data[0].bid;
        askCell.textContent = data[0].ask;
        linkCell.href = `/history?symbol=${symbol}`;
      }
    }).catch(error => {
      console.error('Error fetching bid-ask data:', error);
    });
}

function updateData() {
  const stockSelect = document.getElementById('stock-select');
  const selectedSymbol = stockSelect.value;

  if (selectedSymbol !== '-') {
    const url = new URL(window.location.href);
    url.searchParams.set('symbol', selectedSymbol);
    history.pushState({}, '', url);

    updateChart(selectedSymbol);
    updateBidAsk(selectedSymbol);
  } else {
    // Reset the chart and bid-ask values
    Highcharts.stockChart('stockChart', {
      title: {
        text: '5-Minute Stock Price'
      }
    });
    const bidCell = document.querySelector('.bid');
    const askCell = document.querySelector('.ask');
    bidCell.textContent = '';
    askCell.textContent = '';
    const linkCell = document.querySelector('.Link a');
    linkCell.href = '#';
  }
}

document.addEventListener('DOMContentLoaded', function () {
  const urlParams = new URLSearchParams(window.location.search);
  const symbol = urlParams.get('symbol');

  if (symbol) {
    document.getElementById('stock-select').value = symbol;
    updateData();
  }

  setInterval(updateData, 10000);
});