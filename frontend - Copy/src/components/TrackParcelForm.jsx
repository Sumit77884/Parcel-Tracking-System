import React, { useState } from "react";
import axios from "axios";
import "./TrackParcelForm.css";

export default function TrackParcelForm() {
  const [tracking, setTracking] = useState("");
  const [parcel, setParcel] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleTrack = async (e) => {
    e.preventDefault();
    if (!tracking.trim()) return alert("Please enter a tracking number.");

    setLoading(true);
    setError("");
    setParcel(null);
    try {
      const res = await axios.get(`http://localhost:8080/api/v1/parcels/${tracking}`);
      setParcel(res.data);
    } catch (err) {
      console.error(err);
      setError("Parcel not found. Please check your tracking number.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="track-container">
      <h2>Track Your Parcel</h2>
      <form className="track-form" onSubmit={handleTrack}>
        <input
          type="text"
          placeholder="Enter tracking number"
          value={tracking}
          onChange={(e) => setTracking(e.target.value)}
        />
        <button type="submit" disabled={loading}>
          {loading ? "Tracking..." : "Track"}
        </button>
      </form>

      {error && <p className="error">{error}</p>}

      {parcel && (
        <div className="parcel-card">
          <h3>Tracking Details</h3>
          <p>
            <strong>Status:</strong> {parcel.status}
          </p>
          <p>
            <strong>Receiver:</strong> {parcel.receiverName}
          </p>
          <p>
            <strong>Address:</strong> {parcel.receiverAddress}
          </p>
          <p>
            <strong>Weight:</strong> {parcel.weightKg} kg
          </p>
          <p>
            <strong>Tracking #:</strong> {parcel.trackingNumber}
          </p>
        </div>
      )}
    </div>
  );
}
