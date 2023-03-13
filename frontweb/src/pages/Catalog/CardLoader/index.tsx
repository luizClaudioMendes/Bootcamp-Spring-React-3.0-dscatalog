import ContentLoader from "react-content-loader"

const CardLoader = () => (
  <ContentLoader 
    speed={2}
    width={400}
    height={460}
    viewBox="0 0 400 460"
    backgroundColor="#bbb"
    foregroundColor="#acabab"
  >
    <rect x="1" y="-4" rx="40" ry="40" width="300" height="300" />
  </ContentLoader>
)

export default CardLoader

