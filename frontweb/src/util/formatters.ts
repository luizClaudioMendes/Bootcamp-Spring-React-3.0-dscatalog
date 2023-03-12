export const formatPrice = (price: number) => {
    // https://developer.mozilla.org/pt-BR/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat
    const params = {minimumFractionDigits: 2, maximumFractionDigits: 2};
    
    return new Intl.NumberFormat('pt-BT', params).format(price);
}