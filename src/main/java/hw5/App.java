package hw5;

import org.apache.ibatis.session.SqlSession;

public class App {
    public static void main(String[] args) {
        SqlSession sqlSession =  SqlUtils.openSqlSession();
//        db.model.Products product1 = new db.model.Products();
//        product1.setId(111L);
//        product1.setPrice(333);
//        product1.setCategory_id(444L);
//        product1.setTitle("sql test title");

//        db.dao.ProductsMapper productsMapper = sqlSession.getMapper(db.dao.ProductsMapper.class);
//        db.model.Products products = productsMapper.insert(product1);

        db.dao.CategoriesMapper categoriesMapper = sqlSession.getMapper(db.dao.CategoriesMapper.class);
        db.model.Categories categories = categoriesMapper.selectByPrimaryKey(100);
        System.out.println(categories);
        sqlSession.commit();
    }
}
