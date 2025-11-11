import React from "react";
import { BrowserRouter, Routes, Route, NavLink } from "react-router-dom";
import RegisterParcelForm from "./components/RegisterParcelForm";
import TrackParcelForm from "./components/TrackParcelForm";
import "./App.css";

export default function App() {
  return (
    <BrowserRouter>
      <div className="app-container">
        <nav className="navbar">
          <h2 className="logo">📦 Parcel Tracker</h2>
          <div className="nav-links">
            <NavLink to="/" end>Home</NavLink>
            <NavLink to="/register">Register</NavLink>
            <NavLink to="/track">Track</NavLink>
          </div>
        </nav>

        <main className="content">
          <Routes>
            <Route path="/" element={<LandingPage />} />
            <Route path="/register" element={<RegisterParcelForm />} />
            <Route path="/track" element={<TrackParcelForm />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

function LandingPage() {
  return (
    <div className="landing">
      <h1>Welcome to <span>Parcel Tracking System</span></h1>
      <p>
        Easily register, send, and track your parcels in real-time.  
        A fast and reliable solution for all your delivery needs.
      </p>
      <div className="landing-buttons">
        <NavLink to="/register" className="btn">Register Parcel</NavLink>
        <NavLink to="/track" className="btn btn-outline">Track Parcel</NavLink>
      </div>
    </div>
  );
}