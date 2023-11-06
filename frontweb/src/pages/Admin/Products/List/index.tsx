import ProductCrudCard from 'pages/Admin/Products/ProductCrudCard';
import './styles.css';
import { Link } from 'react-router-dom';

const List = () => {
  const product = {
    id: 1,
    name: 'The Lord of the Rings',
    description:
      'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.',
    price: 90.5,
    imgUrl:
      'https://raw.githubusercontent.com/devsuperior/dscatalog-resources/master/backend/img/1-big.jpg',
    date: '2020-07-13T17:50:07.123450Z',
    categories: [
      {
        id: 2,
        name: 'Eletrônicos',
      },
      {
        id: 1,
        name: 'Computadores',
      },
    ],
  };

  return (
    <>
      <div className="product-crud-bar-container">
        <Link to="/admin/products/create">
          <button className="btn btn-primary text-white btn-crud-add">
            ADICIONAR
          </button>
        </Link>
        <div className="base-card product-filter-container">search bar</div>
      </div>

      <div className="row">
        {/* row é do bootstrap breakpoints */}
        <div className="col-sm-6 col-md-12">
          {/* sm é 576 do bootstrap */}
          {/* mostra um card por linha ate o 576px */}
          {/* depois mostra 2 cards 12/6 */}
          {/* col-md-12 é a partir do 768px volta a ter 1  */}
          {/* 12/12 = 1 */}
          <ProductCrudCard product={product} />
        </div>
        <div className="col-sm-6 col-md-12">
          <ProductCrudCard product={product} />
        </div>
        <div className="col-sm-6 col-md-12">
          <ProductCrudCard product={product} />
        </div>
      </div>
    </>
  );
};

export default List;
