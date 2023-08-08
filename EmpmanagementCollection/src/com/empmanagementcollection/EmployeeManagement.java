package com.empmanagementcollection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class EmployeeManagement {

	public static void main(String[] args) throws Exception {
		int choice = -1;
		LinkedList<Employee> em = new LinkedList<Employee>();

		Scanner sc = new Scanner(System.in);
		Scanner sc1 = new Scanner(System.in);

		File file = new File("EmpProjectInfotrix.txt");
		ObjectOutputStream obj;
		ObjectInputStream ois;

		if (file.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(file));
			em = (LinkedList<Employee>) ois.readObject();
			ois.close();
		}

		ListIterator<Employee> itr = null;

		do {
			System.out.println("\n********* Employee Management System **********\n");
			System.out.println("1. Add Emp \n" + "2. Display emp \n" + "3. Search Emp \n" + "4. Delete Emp\n"
					+ "5. Update Emp \n" + "0. EXIT\n");
			System.out.println("Enter your choice : ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				System.out.println(" Enter Employee ID:");
				int id = sc.nextInt();

				System.out.println("Enter Employee Name:");
				String name = sc1.nextLine();

				System.out.println("Enter Employee Salary:");
				float salary = sc.nextFloat();

				em.add(new Employee(id, name, salary));

				obj = new ObjectOutputStream(new FileOutputStream(file));
				obj.writeObject(em);
				obj.close();

				break;

			case 2:
				System.out.println("===========================================");
				itr = em.listIterator();
				while (itr.hasNext())
					System.out.println(itr.next());
				System.out.println("===========================================");
				break;

			case 3:
				if (file.isFile()) {
					boolean temp = false;
					System.out.println("Enter Employee Id to search ");
					id = sc.nextInt();

					System.out.println("--------------------------------------------------");
					itr = em.listIterator();
					while (itr.hasNext()) {
						Employee e = (Employee) itr.next();
						if (e.id == id) {
							System.out.println(e);
							temp = true;
						}
					}
					if (!temp) {
						System.out.println("record not found... ");
					}
					System.out.println("--------------------------------------------------");

				} else {

					System.out.println("File not found...");
				}

				break;
			case 4:
				if (file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));
					em = (LinkedList<Employee>) ois.readObject();
					ois.close();

					boolean temp = false;
					System.out.println("Enter Employee Id to Delete ");
					int emid = sc1.nextInt();

					System.out.println("--------------------------------------------------");
					itr = em.listIterator();
					while (itr.hasNext()) {
						Employee e = (Employee) itr.next();
						if (e.id == emid) {
							itr.remove();

							obj = new ObjectOutputStream(new FileOutputStream(file));
							obj.writeObject(em);
							obj.close();
							temp = true;
						}
					}
					if (temp) {

						System.out.println("Recored Deleted successfully......");
					} else {
						System.out.println("record not found... ");

					}
					System.out.println("--------------------------------------------------");

				} else {

					System.out.println("File not found...");
				}

				break;

			case 5:
				if (file.isFile()) {
					ois = new ObjectInputStream(new FileInputStream(file));
					em = (LinkedList<Employee>) ois.readObject();
					ois.close();

					boolean temp = false;
					System.out.println("Enter Employee Id to Update ");
					int emid = sc.nextInt();

					System.out.println("--------------------------------------------------");
					itr = em.listIterator();
					while (itr.hasNext()) {
						Employee e = (Employee) itr.next();
						if (e.id == emid) {
							System.out.println(" Enter Employee new ID:");
							int empid = sc.nextInt();
							System.out.println("Enter Employee new Name:");
							String name1 = sc1.nextLine();

							System.out.println("Enter Employee new Salary:");
							float salary1 = sc.nextFloat();
							itr.set(new Employee(empid, name1, salary1));

							temp = true;
						}
					}
					if (temp) {
						obj = new ObjectOutputStream(new FileOutputStream(file));
						obj.writeObject(em);
						obj.close();
						System.out.println("Recored update successfully......");
					} else {
						System.out.println("record not found... ");

					}
					System.out.println("--------------------------------------------------");

				} else {

					System.out.println("File not found...");
				}

				break;
			case 0:
				System.out.println("-------------than you ------------" );

			}

		} while (choice != 0);

	}

}
