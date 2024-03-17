import { createGlobalStyle } from 'styled-components';
import PretendardVariable from '../assets/fonts/PretendardVariable.woff2';

const GlobalStyle = createGlobalStyle`
  @font-face {
    font-family: 'PretendardVariable';
    font-style: normal;
    font-display: swap;
    src: url(${PretendardVariable}) format('woff2'),
        local('PretendardVariable')
  }
`

export default GlobalStyle;