import { ReactComponent as AuthImage } from 'assets/images/auth-image.svg';
import { Route, Switch } from 'react-router-dom';

const Auth = () => {
    return (
        <div className="auth-container">
            <div className="auth-banner-container">
                <h1>Divulgue seus produtos no DS Catalog</h1>
                <p>Faça parte do nosso catálogo de divulgação e aumente a venda de seus produtos</p>
                <AuthImage/>
            </div>

            <div className='auth-form-container'>
                <Switch>
                    <Route path="/admin/auth/login">
                        <h1>card de login</h1>
                    </Route>
                    <Route path="/admin/auth/signup">
                        <h1>card de signup</h1>
                    </Route>
                    <Route path="/admin/auth/recover">
                        <h1>card de recover</h1>
                    </Route>
                </Switch>
            </div>
        </div>
    );
}

export default Auth;