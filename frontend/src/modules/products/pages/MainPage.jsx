import React, { useEffect, useState } from 'react'
import { ProductCard } from '@modules';
import { Link } from 'react-router-dom';
import { request } from '@shared'

function MainPage() {
  const [products, setProducts] = useState([]);

  useEffect(()=>{
    const getAllProducts = async () =>{
      const { data } = await request.get("/product")
      setProducts(data);
    }
    getAllProducts();
  }, [])

  return (
    <div className='grid grid-cols-4'>
      {products.map(product => (
        <Link to ={`/product/${product.id}`} key={product.id}>
          <ProductCard product={product} key={product.id}/>
        </Link>
      ))}
    </div>
  )
}

export default MainPage