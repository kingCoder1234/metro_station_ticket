document.getElementById('ticketForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const start = document.getElementById('start').value;
  const end = document.getElementById('end').value;

  const res = await fetch(`/api/tickets/buy?start=${start}&end=${end}`, {
    method: 'POST'
  });

  const ticket = await res.json();

  document.getElementById('result').innerHTML = `
    <p><strong>Ticket ID:</strong> ${ticket.ticketId}</p>
    <p>From: ${ticket.startStation} → To: ${ticket.endStation}</p>
    <p>Valid Until: ${ticket.expiryTime}</p>
  `;
});
