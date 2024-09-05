
document.addEventListener('DOMContentLoaded', function() {
const symbol = new URLSearchParams(window.location.search).get('symbol');
if (symbol) {
fetch(`/historyentity/${symbol}`)
  .then(response => response.json())
  .then(data => {
    const candlestickData = data.map(item => [
      new Date(item.date).getTime(), // timestamp
      item.open, // open
      item.high, // high
      item.low,  // low
      item.close // close
    ]);

    Highcharts.stockChart('stockChart', {
      rangeSelector: {
        selected: 1
      },
      title: {
        text: `${symbol} Stock Price History`
      },
      time: {
        timezone: 'Asia/Hong_Kong'
      },
      series: [{
        type: 'candlestick',
        name: 'Stock Price',
        data: candlestickData,
        tooltip: {
          valueDecimals: 2
        },
        dataGrouping: {
          enabled: false // Disable data grouping
        }
      }]
    });
  })
  .catch(error => console.error('Error fetching data:', error));
}
});