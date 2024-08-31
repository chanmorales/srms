import React from "react";
import "./App.css";
import { AuthProvider } from "./providers";
import { Routes } from "./routes";

function App() {
  return (
    <AuthProvider>
      <Routes />
    </AuthProvider>
  );
}

export default App;
