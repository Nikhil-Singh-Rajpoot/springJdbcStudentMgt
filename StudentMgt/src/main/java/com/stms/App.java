package com.stms;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.entity.Student;
import com.springJdbc.DataDao;

public class App 
{
	static {
		System.out.println("\n------------------------------------------------------------------------------------------------------------\n");
		System.out.println("\t\t\t         Student Management System");
		System.out.println("\n------------------------------------------------------------------------------------------------------------\n");
	}
    public static void main( String[] args )
    {
       boolean end = false; 
       
       //make application context 
       ApplicationContext context = new AnnotationConfigApplicationContext(Student.class);
       Student student = context.getBean("student", Student.class);
       ApplicationContext context2 = new ClassPathXmlApplicationContext("com/stms/config.xml");
       		DataDao daoImpl =context2.getBean("studentDao",DataDao.class);
       //make scanner class object 
       Scanner scanner = new Scanner(System.in);
       //loop for infinite loop 
       while (!end) {
		System.out.println("\t\t 1. For Add New Student");
		System.out.println("\t\t 2. For Delete Student");
		System.out.println("\t\t 3. For Show Active Student");
		System.out.println("\t\t 4. For Show All Student");
		System.out.println("\t\t 5. For Search Student ");
		System.out.println("\t\t 6. For Exit");
		System.out.print("\t\t Enter Your Choice ::");
		int choice = scanner.nextInt();
		System.out.println();
		//switch case 
		switch (choice) {
		case 1:
			System.out.println("----------------------------------------New Registration---------------------------------------------\n");
			student.setStuId(new Random().nextInt(1000000));
			System.out.print("\t\t Enter Student Name : ");
			student.setStuName(scanner.next());
			//scanner.nextLine();
			System.out.print("\t\t Enter Student Mobile No : ");
			student.setStuMobileNo(scanner.nextLong());
			System.out.print("\t\t Enter Student City :");
			student.setStuCity(scanner.next());
			//scanner.nextLine();
			System.out.print("\t\t Enter are Reguler or Not (yes/no) :");
			String tempString = scanner.next().toLowerCase();
			if (tempString.equals("yes")) {
				student.setActive(true);
			}else {
				student.setActive(false);
			}
			int insert = daoImpl.insert(student);
			if (insert>=1) {
				System.out.println("\n\t\t Registration Seccussfull! Registration :" + student.getStuId());
				System.out.println("\n--------------------------------------------------------------------------------------------------------\n");
			}
			break;
		case 2:
			System.out.println("-------------------------------------------Delete Student----------------------------------------------\n");
			System.out.print("\t\t Enter Student Registration No : ");
			student.setStuId(scanner.nextInt());
			int delete = daoImpl.delete(student.getStuId());
			if (delete>=0) {
				System.out.println("\n\t\t Student Deleted Successfully! ");
				System.out.println("\n-----------------------------------------------------------------------------------------------------\n");
			}
			break;
		case 3:
			System.out.println("---------------------------------All Active Student-----------------------------------------------------\n");
			List<Student> activList = daoImpl.getActivList(true);
			for(Student st1: activList) {
				System.out.println("\t Student Registration No : "+st1.getStuId()+"   Student Name : "+st1.getStuName()+"   Student IsActive : "+st1.isActive());
				System.out.println("\n--------------------------------------------------------------------------------------------------------\n");
			}
			break;
		case 4:
			System.out.println("---------------------------------Active/Not Active Student-----------------------------------------------------\n");
			List<Student> list = daoImpl.getList();
			for(Student st1: list) {
				System.out.println("\t Student Registration No : "+st1.getStuId()+"   Student Name : "+st1.getStuName()+"   Student IsActive : "+st1.isActive());
				System.out.println("\n--------------------------------------------------------------------------------------------------------------------\n");
			}
			break;
		case 5:
			System.out.println("---------------------------------Find Student Details-----------------------------------------------------\n");
			System.out.println("\t\t 1. For By Registration No \t\t 2. For By Name or City ");
			System.out.print("\n\t\t\t\t\t\t Enter Your Choice :: ");
			int choise2 = scanner.nextInt();
			System.out.println();
			//nested switch case
			switch (choise2) {
			case 1:
				System.out.print("\t\t Enter Student Registration No : ");
				student.setStuId(scanner.nextInt());
				Student findStudent = daoImpl.getFindStudent(student.getStuId());
				System.out.println();
				System.out.println("\t\t Student Reg.. : "+findStudent.getStuId()+",   Student Name : "+findStudent.getStuName());
				System.out.println("\t\t Student City : "+findStudent.getStuCity()+",      Student IsActive :"+findStudent.isActive());
				System.out.println("\n--------------------------------------------------------------------------------------------------------------------\n");
				break;
			case 2:
				System.out.print("\t\t Enter Student Name : ");
				student.setStuName(scanner.next());
				System.out.print("\t\t Enter Student City : ");
				student.setStuCity(scanner.next());
				Student findStudent2 = daoImpl.getByNameStudent(student.getStuName(), student.getStuCity());
				System.out.println();
				System.out.println("\t\t Student Reg.. : "+findStudent2.getStuId()+",   Student Name : "+findStudent2.getStuName());
				System.out.println("\t\t Student City : "+findStudent2.getStuCity()+",      Student IsActive :"+findStudent2.isActive());
				System.out.println("\n--------------------------------------------------------------------------------------------------------------------\n");
				break;
				//nested default 
			default:
				System.out.println("\n\t\t Invalid Choice! Sorry!");
				break;
			}
			break;
		case 6:
			System.out.println("---------------------------------Exit Your Application-----------------------------------------------------\n");
			end=true;
			System.exit(0);
			scanner.close();
			break;
			//default statement 
		default:
			System.out.println("\n\t\t Invalid Choice! Sorry!");
			break;
		}// switch case end 
       		}//while loop end 
    	}// main method end
	}//main class end 
