import { ReactComponent as MainImage } from 'assets/images/main-image.svg';
import ButtonIcon from 'components/ButtonIcon';
import { Link } from 'react-router-dom';
import './styles.css';
import { getTokenData, hasAnyRoles, isAuthenticated } from 'util/requests';

const Home = () => {
  return (
    <div className="home-container">

      <h1> Resultado = {hasAnyRoles(['ROLE_ADMIN']) ? 'sim' : 'nao'}</h1>
      <div className="base-card home-card">
        <div className="home-content-container">
          <div>
            <h1>Conheça o melhor catálogo de produtos</h1>
            <p>
              Ajudaremos você a encontrar os melhores produtos disponiveis no
              mercado.
            </p>
          </div>
          <div>
            <Link to="/products">
              <ButtonIcon text="Inicie agora a sua busca"/>
            </Link>
          </div>
        </div>
        <div className="home-image-container">
          <MainImage />
        </div>
      </div>
    </div>
  );
};

export default Home;
