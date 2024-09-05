function updateDateTime() {
  const now = new Date();
  const year = String(now.getFullYear());
  const month = String(now.getMonth() + 1).padStart(2, '0');
  const day = String(now.getDate()).padStart(2, '0');
  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const seconds = String(now.getSeconds()).padStart(2, '0');
  const formattedDateTime = `${year}/${month}/${day} ${hours}:${minutes}:${seconds}`;
  document.getElementById('datetime').textContent = formattedDateTime;
}

// Refresh the page every 10 seconds
setInterval(function() {
  window.location.reload();
}, 10000);

// Update the date and time every second
setInterval(updateDateTime, 1000);

// Initial call to display the current date and time immediately
window.onload = updateDateTime;