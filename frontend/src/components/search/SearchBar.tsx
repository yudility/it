import React, { Dispatch, SetStateAction } from "react";
import styled from "styled-components";
import SearchIcon from '../../assets/Search.svg';
import CancelIcon from '../../assets/Cancel.svg';

const Wrapper = styled.div`
  display: flex;
  width: 327px;
  padding: 10px 16px;
  flex-direction: column;
  align-items: flex-start;
  gap: 10px;
  border-radius: 12px;
  background: #F8F8F8;
`;

const SearchInput = styled.input`
  color: black;
  padding: auto;
  text-align: center;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
  font-weight: 900;
  width: 100%;
  height: 50px;
  font-size: 24px;
  border-color: transparent;
  background: transparent;
`;

const IconButton = styled.img`
  position: absolute;
  top: 135px;
  z-index: 10
`

interface SearchBarProps {
  search: string;
  onChangeSearch: any;
}

export default function SearchBar({ search, onChangeSearch }: SearchBarProps) {
  return (
    <Wrapper>
      <SearchInput value={search} onChange={onChangeSearch} />
      <IconButton style={{right: 70}} src={CancelIcon} alt='취소' />
      <IconButton style={{right: 40, top: 138}} src={SearchIcon} alt='검색' />
    </Wrapper>
  );
}
