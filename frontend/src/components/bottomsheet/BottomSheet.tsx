import useBottomSheet from "../../hooks/useBottomSheet";
import { useState, useEffect } from "react";
import * as S from "./BottomSheet.style";
import Header from "./Header";

const BottomSheet = ({ children, mode }: { children: any; mode: string; }) => {
  const { onDragEnd, controls } = useBottomSheet();
  const [height, setHeight] = useState<number>(350);
  useEffect(() => {
    if (mode === 'onSearch') {
      setHeight(0);
    } else if (mode === 'beforeSearch') {
      setHeight(350);
    } else if (mode === 'result') {
      setHeight(680);
    }
  }, [mode]);

  useEffect(() => {
    // height 상태가 변경될 때마다 애니메이션을 다시 시작
    controls.start({
      y: height,
      transition: {
        type: "spring",
        damping: 40,
        stiffness: 400,
      },
    });
  }, [height, controls]);

  return (
    <S.Wrapper
      drag='y'
      onDragEnd={onDragEnd}
      initial='hidden'
      animate={controls}
      transition={{
        type: "spring",
        damping: 40,
        stiffness: 400,
      }}
      variants={{
        visible: { y: height },
        hidden: { y: "100%" },
      }}
      dragConstraints={{ top: 100 }}
      dragElastic={0.2}
    >
      <Header />
      <S.ContentWrapper>{children}</S.ContentWrapper>
    </S.Wrapper>
  );
};

export default BottomSheet;