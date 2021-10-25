package dao;

public class DAOFactory {
	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAOImpl();
	}
	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
}