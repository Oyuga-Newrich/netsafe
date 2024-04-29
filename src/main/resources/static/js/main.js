//$(document).ready(function() {
//    $('.dropdown-toggle').dropdown();
//});
const today = new Date().toISOString().slice(0, 10);
document.getElementById('date').value = today;

function updateCurrentTime() {
    let now = new Date();
    let formattedTime = now.toLocaleTimeString('en-US', { hour12: true });
    document.getElementById('currentTime').textContent = formattedTime;
}

updateCurrentTime();
setInterval(updateCurrentTime, 1000); // Update every second