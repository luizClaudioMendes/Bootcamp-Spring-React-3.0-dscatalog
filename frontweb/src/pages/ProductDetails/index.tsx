import { ReactComponent as ArrowIcon } from 'assets/images/arrow.svg';
import axios from 'axios';
import ProductPrice from 'components/ProductPrice';
import { Link } from 'react-router-dom';
import { Product } from 'types/product';
import { BASE_URL } from 'util/requests';
import './styles.css';

const ProductDetails = () => {
  /* 
  // forma incorreta!!!
  let product : Product;

  // forma incorreta!!!
  axios.get(BASE_URL + "/products/2")// assincrona
  // .then aguarda a resposta
  .then(response => {
    console.log(response.data)
  });  
  */

  return (
    <div className="product-detail-container">
      <div className="base-card product-details-card">
        <Link to="/products">
          <div className="goback-containter">
            <ArrowIcon />
            <h2>VOLTAR</h2>
          </div>
        </Link>
        <div className="row">
          <div className="col-xl-6">
            <div className="img-container">
              <img
                src="https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/2-big.jpg"
                alt="nome do produto"
              />
            </div>
            <div className="name-price-container">
              <h1>NOME DO PRODUTO</h1>
              <ProductPrice price={2345.67} />
            </div>
          </div>
          <div className="col-xl-6">
            <div className="description-container">
              <h2>DESCRICAO DO PRODUTO</h2>
              <p>
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Eum,
                ab.
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
