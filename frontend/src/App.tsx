import "./App.css";
import Map from "./pages/Map";
import { PlaceProvider } from "./contexts/PlaceContext";

function App() {
  return (
    <PlaceProvider>
      <Map />
    </PlaceProvider>
  );
}

export default App;
