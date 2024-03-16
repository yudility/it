import { useState, useMemo } from 'react';

const Locations = () => {
  const [location, setLocation] = useState<{latitude: number; longitude: number} | string>('');

  useMemo(()=>{
    if (navigator.geolocation){
      navigator.geolocation.getCurrentPosition(success, error);
    }

    function success(position: any){
      setLocation({
        latitude: position.coords.latitude,
        longitude: position.coords.longitude
      });
    }

    function error(){
      setLocation({
        latitude: 37.559716,
        longitude: 126.945468
      })
      console.log("위치 받기 실패");
    }
  }, [navigator.geolocation.getCurrentPosition])

  return ( location )
}

export default Locations;