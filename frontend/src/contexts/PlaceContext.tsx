import { createContext, ReactNode, useState, Dispatch, SetStateAction } from 'react';

export const PlaceContext = createContext<{ places: any[], setPlaces: Dispatch<SetStateAction<any[]>> }>({
  places: [],
  setPlaces: () => {}
})

export const PlaceProvider = ({children}: {children:ReactNode}) => {
  const [places, setPlaces] = useState<any[]>([]);
  return (
    <PlaceContext.Provider value={{ places, setPlaces }}>
      {children}
    </PlaceContext.Provider>
  )
}