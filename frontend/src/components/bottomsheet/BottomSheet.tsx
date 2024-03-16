import useBottomSheet from "../../hooks/useBottomSheet";

import * as S from "./BottomSheet.style";
import Header from "./Header";

const BottomSheet = ({ children }: any) => {
  const { onDragEnd, controls } = useBottomSheet();

  return (
    <S.Wrapper
      drag="y"
      onDragEnd={onDragEnd}
      initial="hidden"
      animate={controls}
      transition={{
        type: "spring",
        damping: 40,
        stiffness: 400
      }}
      variants={{
        visible: { y: 120 },
        hidden: { y: "100%" }
      }}
      dragConstraints={{ top: 0 }}
      dragElastic={0.2}
    >
      <Header />
      <S.ContentWrapper>{children}</S.ContentWrapper>
    </S.Wrapper>
  );
};

export default BottomSheet;
