import './styles.css';

const Form = () => {
  return (
    <div className="product-crud-container">
      <div className="base-card product-crud-form-card">
        <h1 className="product-crud-form-title">DADOS DO PRODUTO</h1>
        {/* o form deve conter os inputs e os botoes */}
        <form action="">
          <div className="row product-crud-inputs-container">
            {/* a partir do breakpoint lg quebrar em 2 colunas */}
            <div className="col-lg-6 product-crud-input-left-container">
                <div className='margin-bottom-30'>
                    <input type='text' className='form-control base-input' />

                </div>
                <div className='margin-bottom-30'>
                    <input type='text' className='form-control base-input' />

                </div>
                <div className=''>
                    <input type='text' className='form-control base-input' />

                </div>
            </div>
            <div className="col-lg-6">
                <textarea name='' rows={10}  className='form-control base-input'>

                </textarea>
            </div>
          </div>
          <div className='product-crud-buttons-container'>
            <button className='btn btn-outline-danger'>CANCELAR</button>
            <button className='btn btn-primary'>SALVAR</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Form;
