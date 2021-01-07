package ioc;

import web.ActionFactory;

import java.util.HashMap;
import java.util.Map;


public class IoCConfigurer {
	public static void configure() throws IoCException {
		/* registration of actions */
		IoCContainer.registerFactory("web.Action", "web.ActionFactory");

		Map<String, String> specialityActionsDependencies = new HashMap<>();
		specialityActionsDependencies.put("service.SpecialityService", "setSpecialityService");

		Map<String, String> specialityActionsDependencies1 = new HashMap<>();
		specialityActionsDependencies1.put("service.SpecialityService", "setSpecialityService");
		specialityActionsDependencies1.put("service.DoctorService", "setDoctorService");

		ActionFactory.registerAction("/speciality/index", "web.speciality.SpecialityIndexActionImpl");
		DIContainer.registerClass("web.speciality.SpecialityIndexActionImpl", specialityActionsDependencies);
		ActionFactory.registerAction("/speciality/edit", "web.speciality.SpecialityEditActionImpl");
		DIContainer.registerClass("web.speciality.SpecialityEditActionImpl", specialityActionsDependencies);
		ActionFactory.registerAction("/speciality/save", "web.speciality.SpecialitySaveActionImpl");
		DIContainer.registerClass("web.speciality.SpecialitySaveActionImpl", specialityActionsDependencies1);
		ActionFactory.registerAction("/speciality/delete", "web.speciality.SpecialityDeleteActionImpl");
		DIContainer.registerClass("web.speciality.SpecialityDeleteActionImpl", specialityActionsDependencies);

		Map<String, String> doctorActionsDependencies1 = new HashMap<>();
		doctorActionsDependencies1.put("service.DoctorService", "setDoctorService");
		Map<String, String> doctorActionsDependencies2 = new HashMap<>();
		doctorActionsDependencies2.put("service.DoctorService", "setDoctorService");
		doctorActionsDependencies2.put("service.SpecialityService", "setSpecialityService");

		ActionFactory.registerAction("/doctor/index", "web.doctor.DoctorIndexActionImpl");
		DIContainer.registerClass("web.doctor.DoctorIndexActionImpl", doctorActionsDependencies2);
		ActionFactory.registerAction("/doctor/edit", "web.doctor.DoctorEditActionImpl");
		DIContainer.registerClass("web.doctor.DoctorEditActionImpl", doctorActionsDependencies2);
		ActionFactory.registerAction("/doctor/save", "web.doctor.DoctorSaveActionImpl");
		DIContainer.registerClass("web.doctor.DoctorSaveActionImpl", doctorActionsDependencies2);
		ActionFactory.registerAction("/doctor/delete", "web.doctor.DoctorDeleteActionImpl");
		DIContainer.registerClass("web.doctor.DoctorDeleteActionImpl", doctorActionsDependencies1);

		Map<String, String> userActionsDependencies1 = new HashMap<>();
		userActionsDependencies1.put("service.UserService", "setUserService");
		Map<String, String> userActionsDependencies2 = new HashMap<>();
		userActionsDependencies2.put("service.UserService", "setUserService");
		userActionsDependencies2.put("service.RoleService", "setRoleService");

		ActionFactory.registerAction("/user/index", "web.user.UserIndexActionImpl");
		DIContainer.registerClass("web.user.UserIndexActionImpl", userActionsDependencies1);
		ActionFactory.registerAction("/user/edit", "web.user.UserEditActionImpl");
		DIContainer.registerClass("web.user.UserEditActionImpl", userActionsDependencies2);
		ActionFactory.registerAction("/user/save", "web.user.UserSaveActionImpl");
		DIContainer.registerClass("web.user.UserSaveActionImpl", userActionsDependencies2);
		ActionFactory.registerAction("/user/delete", "web.user.UserDeleteActionImpl");
		DIContainer.registerClass("web.user.UserDeleteActionImpl", userActionsDependencies1);

		Map<String, String> authActionsDependencies = new HashMap<>();
		authActionsDependencies.put("service.UserService", "setUserService");
		ActionFactory.registerAction("/login", "web.auth.LoginActionImpl");
		DIContainer.registerClass("web.auth.LoginActionImpl", authActionsDependencies);
		ActionFactory.registerAction("/logout", "web.auth.LogoutActionImpl");

		/* registration of factory for connections */
		IoCContainer.registerFactory("java.sql.Connection", "pool.ConnectionFactory");

		Map<String, String> transactionDependencies = new HashMap<>();
		transactionDependencies.put("java.sql.Connection", "setConnection");
		IoCContainer.registerClass("transaction.Transaction", "transaction.TransactionImpl");
		DIContainer.registerClass("transaction.TransactionImpl", transactionDependencies);

		/* registration of DAO */
		Map<String, String> daoDependencies = new HashMap<>();
		daoDependencies.put("java.sql.Connection", "setConnection");


		IoCContainer.registerClass("dao.SpecialityDao", "dao.mysql.SpecialityMySqlDaoImpl");
		DIContainer.registerClass("dao.mysql.SpecialityMySqlDaoImpl", daoDependencies);
		IoCContainer.registerClass("dao.DoctorDao", "dao.mysql.DoctorMySqlDaoImpl");
		DIContainer.registerClass("dao.mysql.DoctorMySqlDaoImpl", daoDependencies);
		IoCContainer.registerClass("dao.UserDao", "dao.mysql.UserMySqlDaoImpl");
		DIContainer.registerClass("dao.mysql.UserMySqlDaoImpl", daoDependencies);
		IoCContainer.registerClass("dao.RoleDao", "dao.mysql.RoleMySqlDaoImpl");
		DIContainer.registerClass("dao.mysql.RoleMySqlDaoImpl", daoDependencies);

		Map<String, String> specialityServiceDependencies = new HashMap<>();
		specialityServiceDependencies.put("transaction.Transaction", "setTransaction");
		specialityServiceDependencies.put("dao.SpecialityDao", "setSpecialityDao");
		IoCContainer.registerClass("service.SpecialityService", "service.SpecialityServiceImpl");
		DIContainer.registerClass("service.SpecialityServiceImpl", specialityServiceDependencies);

		Map<String, String> doctorServiceDependencies = new HashMap<>();
		doctorServiceDependencies.put("transaction.Transaction", "setTransaction");
		doctorServiceDependencies.put("dao.SpecialityDao", "setSpecialityDao");
		doctorServiceDependencies.put("dao.DoctorDao", "setDoctorDao");
		IoCContainer.registerClass("service.DoctorService", "service.DoctorServiceImpl");
		DIContainer.registerClass("service.DoctorServiceImpl", doctorServiceDependencies);

		Map<String, String> userServiceDependencies = new HashMap<>();
		userServiceDependencies.put("transaction.Transaction", "setTransaction");
		userServiceDependencies.put("dao.UserDao", "setUserDao");
		IoCContainer.registerClass("service.UserService", "service.UserServiceImpl");
		DIContainer.registerClass("service.UserServiceImpl", userServiceDependencies);

		Map<String, String> roleServiceDependencies = new HashMap<>();
		roleServiceDependencies.put("transaction.Transaction", "setTransaction");
		roleServiceDependencies.put("dao.RoleDao", "setRoleDao");
		IoCContainer.registerClass("service.RoleService", "service.RoleServiceImpl");
		DIContainer.registerClass("service.RoleServiceImpl", roleServiceDependencies);
	}
}