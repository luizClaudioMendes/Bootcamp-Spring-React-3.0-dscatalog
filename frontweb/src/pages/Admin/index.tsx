import { Switch } from 'react-router-dom';
import Navbar from './Navbar';
import { Users } from './user';
import PrivateRoute from 'components/PrivateRoute';

import './styles.css';

const Admin = () => {
  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content">
        <Switch>
          <PrivateRoute path="/admin/products">
            <h1>Product crud</h1>
          </PrivateRoute>
          <PrivateRoute path="/admin/categories">
            <h1>categories crud</h1>
          </PrivateRoute>
          <PrivateRoute path="/admin/users">
            <Users />
          </PrivateRoute>
        </Switch>
      </div>
    </div>
  );
};

export default Admin;
