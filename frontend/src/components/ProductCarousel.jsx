import { Link } from "react-router-dom";
import { Carousel, Image } from "react-bootstrap";
import Loader from "./Loader";
import Message from "./Message";
import { useGetProductsQuery } from "../slices/productApiSlice";

const ProductCarousel = () => {
  const { data, isLoading, error } = useGetProductsQuery();

  return isLoading ? (
    <Loader />
  ) : error ? (
    <Message variant="danger">{error?.data?.message || error.error}</Message>
  ) : (
    <Carousel variant="dark" pause="hover" className="mb-4">
      {data.map((product, index) => (
        <Carousel.Item key={index}>
          <Link to={`/product/${product.id}`}>
            <div className="d-flex justify-content-center">
              <Image
                src={product.image}
                alt={product.name}
                fluid
                className="center-image"
              />
            </div>
            <Carousel.Caption className="carousel-caption">
              <h2 className="text-white text-right">
                {product.name} (${product.price})
              </h2>
            </Carousel.Caption>
          </Link>
        </Carousel.Item>
      ))}
    </Carousel>
  );
};

export default ProductCarousel;
