import React, { Dispatch, SetStateAction } from "react";
import styled from "styled-components";
import CancelIcon from "../../assets/Cancel.svg";

const Wrapper = styled.div`
  display: flex;
  width: 327px;
  padding: 10px 16px;
  flex-direction: row;
  align-items: center;
  gap: 10px;
  border-radius: 12px;
  background: #f8f8f8;
  margin-top: 10px;
`;

const SearchInput = styled.input`
  color: black;
  padding: auto;
  text-align: flex-start;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
  font-weight: 400;
  width: 100%;
  height: 50px;
  font-size: 16px;
  border-color: transparent;
  background: transparent;
  font-family: PretendardVariable;
`;

const IconButton = styled.img`
  z-index: 2;
`;

export interface SearchBarProps {
  search: string;
  setSearch: Dispatch<SetStateAction<string>>;
  onChangeSearch: any;
}

export default function SearchBar({
  search,
  setSearch,
  onChangeSearch,
}: SearchBarProps) {
  return (
    <Wrapper>
      <SearchInput
        value={search}
        onChange={onChangeSearch}
        placeholder='장소를 검색하세요'
      />
      {search.length > 0 && (
        <IconButton
          src={CancelIcon}
          alt='취소'
          onClick={() => setSearch("")}
        />
      )}
    </Wrapper>
  );
}
