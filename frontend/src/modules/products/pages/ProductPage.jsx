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

  return (
    <div className=''>
      <img src={product?.picture} alt="product" />
      <h3>{product?.name}</h3>
      <h4>{product?.category.name}</h4>
      <p>{product?.description}</p>
      <p>{product?.stock}</p>
      <p>{product?.price}</p>
    </div>
  )
}

export default ProductPage