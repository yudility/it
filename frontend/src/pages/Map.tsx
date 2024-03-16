import React, { useState } from "react";
import KakaoMap from "../components/map/KakaoMap";
import SearchBar from "../components/search/SearchBar";

export default function Map() {
  const [search, setSearch] = useState<string>('');
  const onChangeSearch = (e: any) => {
    e.preventDefault();
    setSearch(e.target.value);
  };
  return (
    <>
      <SearchBar search={search} onChangeSearch={onChangeSearch} />
      <KakaoMap search={search} />
    </>
  )
}
