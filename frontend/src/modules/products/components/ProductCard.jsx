import React from 'react'

function ProductCard({ product }) {
  return (
    <div className=''>
        <img src={product.picture} alt={product.name} className='w-[14rem]'/>
        <h3>{ product.name }</h3>
        <h4>{ product.category.name }</h4>
        <p>{product.description}</p>
        <p>${product.price}</p>
    </div>
  )
}

export default ProductCard