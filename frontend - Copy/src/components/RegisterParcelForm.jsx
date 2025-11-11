import React, { useState } from "react";
import axios from "axios";
import "./RegisterParcelForm.css";

export default function RegisterParcelForm() {
  const [form, setForm] = useState({
    receiverName: "",
    receiverPhone: "",
    receiverAddress: "",
    weightKg: "",
  });
  const [trackingNumber, setTrackingNumber] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const res = await axios.post("http://localhost:8080/api/v1/parcels", {
        ...form,
        senderId: 1,
      });
      setTrackingNumber(res.data.trackingNumber);
    } catch (err) {
      alert("Error registering parcel. Please check your backend connection.");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="register-form-container">
      <h2>Register New Parcel</h2>
      <form className="parcel-form" onSubmit={handleSubmit}>
        <input
          name="receiverName"
          placeholder="Receiver Name"
          onChange={handleChange}
          required
        />
        <input
          name="receiverPhone"
          placeholder="Phone"
          onChange={handleChange}
          required
        />
        <input
          name="receiverAddress"
          placeholder="Address"
          onChange={handleChange}
          required
        />
        <input
          name="weightKg"
          placeholder="Weight (kg)"
          type="number"
          step="0.1"
          onChange={handleChange}
          required
        />
        <button type="submit" disabled={loading}>
          {loading ? "Registering..." : "Register Parcel"}
        </button>
      </form>

      {trackingNumber && (
        <div className="tracking-result">
          <p>Parcel registered successfully!</p>
          <p>
            Tracking Number: <b>{trackingNumber}</b>
          </p>
        </div>
      )}
    </div>
  );
}