import React, { useEffect, useState } from "react";
import "./StationList.css";

const StationList = () => {
  const [stations, setStations] = useState([]);
  const [startStation, setStartStation] = useState("");
  const [endStation, setEndStation] = useState("");
  const [message, setMessage] = useState("");
  const [ticketId, setTicketId] = useState(
    () => localStorage.getItem("ticketId") || ""
  );
  const [ticketPrice, setTicketPrice] = useState("");
  const [useType, setUseType] = useState("entry");
  const [useResponse, setUseResponse] = useState("");
  const [statusResponse, setStatusResponse] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/stations")
      .then((res) => res.json())
      .then((data) => setStations(data))
      .catch((err) => console.error("Error fetching stations:", err));
  }, []);

  const handleBuyTicket = () => {
    if (!startStation || !endStation) {
      setMessage("Please select both start and end stations.");
      return;
    }

    const startObj = stations.find((s) => s.name === startStation);
    const endObj = stations.find((s) => s.name === endStation);

    if (!startObj || !endObj) {
      setMessage("Selected stations are invalid.");
      return;
    }

    const calculatedPrice = Math.abs(startObj.price - endObj.price);
    setTicketPrice(calculatedPrice);

    fetch(
      `http://localhost:8080/api/tickets/buy?start=${encodeURIComponent(
        startStation
      )}&end=${encodeURIComponent(endStation)}`,
      { method: "POST" }
    )
      .then((res) => {
        if (!res.ok) throw new Error("Failed to buy ticket");
        return res.json();
      })
      .then((data) => {
        setMessage("Ticket purchased successfully!");
        setTicketId(data.ticketId);
        localStorage.setItem("ticketId", data.ticketId);
        if (data.price) setTicketPrice(data.price);
      })
      .catch((err) => {
        setMessage("Error buying ticket: " + err.message);
      });
  };

  const handleUseTicket = () => {
    if (!ticketId) {
      setUseResponse("Please enter a ticket ID.");
      return;
    }

    fetch(
      `http://localhost:8080/api/tickets/use?ticketId=${ticketId}&type=${useType}`,
      { method: "POST" }
    )
      .then((res) => res.text())
      .then((data) => setUseResponse(data))
      .catch((err) => setUseResponse("Error: " + err.message));
  };

  const handleCheckStatus = () => {
    if (!ticketId) {
      setStatusResponse("Please enter a ticket ID.");
      return;
    }

    fetch(`http://localhost:8080/api/tickets/status?ticketId=${ticketId}`)
      .then((res) => res.text())
      .then((data) => setStatusResponse(data))
      .catch((err) => setStatusResponse("Error: " + err.message));
  };

  return (
    <div className="wrapper">
      <div className="card">
        <h2 className="heading">🎫 Buy Metro Ticket</h2>

        <div className="field">
          <label className="label">Start Station:</label>
          <select
            value={startStation}
            onChange={(e) => setStartStation(e.target.value)}
            className="select"
          >
            <option value="">--Select--</option>
            {stations.map((station, idx) => (
              <option key={idx} value={station.name}>
                {station.name}
              </option>
            ))}
          </select>
        </div>

        <div className="field">
          <label className="label">End Station:</label>
          <select
            value={endStation}
            onChange={(e) => setEndStation(e.target.value)}
            className="select"
          >
            <option value="">--Select--</option>
            {stations.map((station, idx) => (
              <option key={idx} value={station.name}>
                {station.name}
              </option>
            ))}
          </select>
        </div>

        <button onClick={handleBuyTicket} className="btn btn-blue">
          Buy Ticket
        </button>

        {ticketId && (
          <div className="info">
            <strong>Ticket ID:</strong> {ticketId}
            {ticketPrice && (
              <span>
                {" "}
                | <strong>Price: ₹{ticketPrice}</strong>
              </span>
            )}
          </div>
        )}

        {message && (
          <div
            className={`message ${message.startsWith("Error") ? "error" : "success"}`}
          >
            {message}
          </div>
        )}

        <hr />

        <h2 className="heading">🎟️ Use Ticket</h2>

        <div className="field">
          <label className="label">Ticket ID:</label>
          <input
            className="input"
            value={ticketId}
            onChange={(e) => setTicketId(e.target.value)}
            placeholder="Ticket ID"
          />
        </div>

        <div className="field">
          <label className="label">Type:</label>
          <select
            value={useType}
            onChange={(e) => setUseType(e.target.value)}
            className="select"
          >
            <option value="entry">Entry</option>
            <option value="exit">Exit</option>
          </select>
        </div>

        <button onClick={handleUseTicket} className="btn btn-green">
          Use Ticket
        </button>

        {useResponse && (
          <div className="response-box">
            <strong>Response:</strong> {useResponse}
          </div>
        )}

        <hr />

        <h2 className="heading">📋 Check Ticket Status</h2>

        <div className="field">
          <label className="label">Ticket ID:</label>
          <input
            className="input"
            value={ticketId}
            onChange={(e) => setTicketId(e.target.value)}
            placeholder="Ticket ID"
          />
        </div>

        <button onClick={handleCheckStatus} className="btn btn-yellow">
          Check Status
        </button>

        {statusResponse && (
          <div className="status-box">
            <strong>Status:</strong> {statusResponse}
          </div>
        )}
      </div>
    </div>
  );
};

export default StationList;
