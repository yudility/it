import "./App.css";
import Map from "./pages/Map";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { PlaceProvider } from "./contexts/PlaceContext";

function App() {
  return (
    <PlaceProvider>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Map />} />
        </Routes>
      </BrowserRouter>
    </PlaceProvider>
  );
}

export default App;
