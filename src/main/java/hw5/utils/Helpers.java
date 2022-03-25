package hw5.utils;

import db.model.Categories;
import db.model.Products;
import hw5.dto.response.JsonCategory;
import hw5.dto.response.JsonProduct;
import org.apache.ibatis.session.SqlSession;

public class Helpers {
    public static void main(String[] args) {

    }

    public static boolean isCategoryExist(int id) {
        SqlSession sqlSession = SqlUtils.openSqlSession();
        db.dao.CategoriesMapper categoriesMapper = sqlSession.getMapper(db.dao.CategoriesMapper.class);
        db.model.Categories categories = categoriesMapper.selectByPrimaryKey(id);
        return categories != null;
    }

    public static boolean isProductExist(long id) {
        SqlSession sqlSession = SqlUtils.openSqlSession();
        db.dao.ProductsMapper productsMapper = sqlSession.getMapper(db.dao.ProductsMapper.class);
        db.model.Products products = productsMapper.selectByPrimaryKey(id);
        return products != null;
    }

    public static JsonProduct getProductEntity(long productID) {
        SqlSession sqlSession = SqlUtils.openSqlSession();
        db.dao.ProductsMapper productsMapper = sqlSession.getMapper(db.dao.ProductsMapper.class);

        db.dao.CategoriesMapper categoriesMapper = sqlSession.getMapper(db.dao.CategoriesMapper.class);
        int categoryID = productsMapper.selectByPrimaryKey(productID).getCategory_id();

        return new JsonProduct()
                .withId(productID)
                .withTitle(productsMapper.selectByPrimaryKey(productID).getTitle())
                .withPrice(productsMapper.selectByPrimaryKey(productID).getPrice())
                .withCategoryTitle(categoriesMapper.selectByPrimaryKey(categoryID).getTitle());
    }

    public static JsonCategory createCategory(int id, String title){
        JsonCategory createCategory = null;

        if (!isCategoryExist(id)) {
            SqlSession sqlSession = SqlUtils.openSqlSession();

            Categories categories = new Categories();
            categories.setId(id);
            categories.setTitle(title);

            sqlSession.insert("insertByID", categories);
            sqlSession.commit();

            db.dao.CategoriesMapper categoriesMapper = sqlSession.getMapper(db.dao.CategoriesMapper.class);
            createCategory = new JsonCategory()
                    .withId(categoriesMapper.selectByPrimaryKey(id).getId())
                    .withTitle(categoriesMapper.selectByPrimaryKey(id).getTitle());
        } else {
            System.out.println("такой объект уже существует в БД. измени ID !!!");
        }
        return createCategory;
    }

    public static JsonProduct createProduct(Long key, String title, int price, int catID) {
        JsonProduct createProduct = null;

        if (!isProductExist(key)) {
            SqlSession sqlSession = SqlUtils.openSqlSession();
            Products products = new Products();
            products.setId(key);
            products.setTitle(title);
            products.setPrice(price);
            products.setCategory_id(catID);

            sqlSession.insert("insertProductByID", products);
            sqlSession.commit();

            db.dao.ProductsMapper productsMapper = sqlSession.getMapper(db.dao.ProductsMapper.class);
            db.dao.CategoriesMapper categoriesMapper = sqlSession.getMapper(db.dao.CategoriesMapper.class);
            int categoryID = productsMapper.selectByPrimaryKey(key).getCategory_id();

            createProduct = new JsonProduct()
                    .withId(key)
                    .withTitle(productsMapper.selectByPrimaryKey(key).getTitle())
                    .withPrice(productsMapper.selectByPrimaryKey(key).getPrice())
                    .withCategoryTitle(categoriesMapper.selectByPrimaryKey(categoryID).getTitle());
        } else {
            System.out.println("такой объект уже существует в БД. измени ID !!!");
        }
       return createProduct;

    }

    public static void deletedProduct(Long id){
        SqlSession sqlSession = SqlUtils.openSqlSession();
        sqlSession.delete("db.dao.ProductsMapper.deleteByPrimaryKey",id);
        sqlSession.commit();
    }

    public static void deletedCategory(Integer id){
        SqlSession sqlSession = SqlUtils.openSqlSession();
        sqlSession.delete("db.dao.CategoriesMapper.deleteByPrimaryKey",id);
        sqlSession.commit();
    }
}
