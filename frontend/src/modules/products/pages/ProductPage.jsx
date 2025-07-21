import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import { request } from '@shared'

function ProductPage() {
  const { id } = useParams();
  const [product, setProduct] = useState();

  useEffect(()=>{
    const getProductById = async () =>{
      const { data } = await request.get(`/product/${id}`);
      setProduct(data);
    }
    getProductById();
  }, [])

  const formatter = Intl.NumberFormat('en-US', {style: 'currency', currency: 'MXN', minimumFractionDigits: 2})

  return (
    <div className='flex items-center'>
      <img src={product?.picture} alt="product" className='flex max-w-7xl w-1/2'/>
      <div className='flex flex-col items-center w-1/2 text-3xl h-full'>
        <h3 className='font-bold'>{product?.name}</h3>
        <h4>Categoría: {product?.category.name}</h4>
        <p>{product?.description}</p>
        <p>Disponibles: {product?.stock}</p>
        <p>{formatter.format(product?.price)}</p>
      </div>
    </div>
  )
}

export default ProductPage