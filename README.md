# Project component: shop-recommender
Component of Cloud Project (UfPrCloud2);

Composed of:
- catalog (shop-catalog)
- purchase (shop-purchase)
- shop-rating
- shop-recommender
- registry
- gateway

## Mini API docs
**Get recommended products by productId, userId**
```
GET /api/reccomendations/{productId}/{userId}
```
- Path Parameters: productId, userId
- **Return** an array of 5 Products (JSON object) 
