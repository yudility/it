import React, { useState } from "react";
import KakaoMap from "../components/map/KakaoMap";
import SearchBar from "../components/common/SearchBar";

export default function Map() {
  const [search, setSearch] = useState<string>('');
  return (
    <>
      <SearchBar search={search} setSearch={setSearch} />
      <KakaoMap search={search} />
    </>
  )
}
