import React from 'react'

function ProductCard({ product }) {
  const formatter = Intl.NumberFormat('en-US', {style: 'currency', currency: 'MXN', minimumFractionDigits: 2})

  return (
    <div className='bg-transparent max-w-sm rounded-xl shadow-2xl transition-transform hover:scale-101 flex flex-col items-center p-5'>
        <img src={product.picture} alt={product.name} className='object-cover w-[20rem] h-[20rem] p-2'/>
        <h3 className='text-xl font-semibold'>{ product.name }</h3>
        <p>{formatter.format(product.price)}</p>
    </div>
  )
}

export default ProductCard