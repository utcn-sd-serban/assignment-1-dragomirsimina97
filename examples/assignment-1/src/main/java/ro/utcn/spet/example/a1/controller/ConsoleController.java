package ro.utcn.spet.example.a1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.spet.example.a1.entity.QTag;
import ro.utcn.spet.example.a1.entity.Question;
import ro.utcn.spet.example.a1.entity.Tag;
import ro.utcn.spet.example.a1.entity.Userss;
import ro.utcn.spet.example.a1.exception.UserssNotFoundException;
import ro.utcn.spet.example.a1.service.QtagManagementService;
import ro.utcn.spet.example.a1.service.QuestionManagementServive;
import ro.utcn.spet.example.a1.service.TagManagementService;
import ro.utcn.spet.example.a1.service.UserssManagementService;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
// Command line runners are executed by Spring after the initialization of the app has been done
// https://www.baeldung.com/spring-boot-console-app
public class ConsoleController implements CommandLineRunner {
	private final Scanner scanner = new Scanner(System.in);
	private final UserssManagementService userssmanagementservice;
	private final QuestionManagementServive questionManagementServive;
	private final TagManagementService tagManagementServive;
	private final QtagManagementService qtagManagementServive;

	@Override
	public void run(String... args) {


		print("Welcome to my project.");
		print("Enter your username");
		String user=scanner.nextLine();
		print("Enter your password");
		String pass=scanner.nextLine();

		boolean done = false;
		while (!done)

		{
			if ( userssmanagementservice.findByName(user, pass).isPresent()  ) {

				print("Enter a command: ");

				String command = scanner.nextLine().trim();
				try {
					done = handleCommand(command);
				} catch (UserssNotFoundException userssNotFoundException) {
					print("The user with the given ID was not found!");
				}}
				else{print("not ok");

				print("Enter your username");
				 user=scanner.nextLine();
				print("Enter your password");
				 pass=scanner.nextLine();

				}
			}


		}

	private boolean handleCommand(String command) {
		switch (command) {
			case "list":
				handleList();
				return false;
			case "list-q":
				handleListQ();
				return false;
			case "add":
				handleAdd();
				return false;

			case "remove":
				handleRemove();
				return false;
			case "remove-q":
				handleRemoveQ();
				return false;
			case "add-q":
				handleAddQ();
				return false;
			case "update":
				handleUpdate();
				return false;
			case "filter title":
				handleFilterTitle();
				return false;
			case "exit":
				return true;
			default:
				print("Unknown command. ");
				return false;
		}}


	private void handleList() {
		userssmanagementservice.listUserss().forEach(s -> print(s.toString()));

	}
	private void handleListQ() { questionManagementServive.question().forEach(s -> print(s.toString()));
	}
	private void handleAdd() {
		print("name:");
		String name = scanner.next().trim();
		print("password:");
		String password = scanner.next().trim();
		Userss userss = userssmanagementservice.addUserss(name, password);
		print("Created userss: " + userss + ".");
	}

private void handleAddQ(){
		print("title:");
		String title=scanner.nextLine().trim();
		print("text");
		String text=scanner.nextLine().trim();
		Question question=questionManagementServive.insert(title,text);
		print("Created question: " + question + ".");


	    boolean stop = false;
	    while(!stop){
			print("You have to enter tags. When done, write x");
			String tagName=scanner.nextLine().trim();
			if (tagName.equals("x"))
				stop = true;
			handleinsert(question, tagName);


		}

}

private void handleRemoveQ() {
		print("Question ID:");
		int id = scanner.nextInt();
		questionManagementServive.remove(id);
	}

private void handleUpdate(){
     print("id");
     int id=scanner.nextInt();
	print("title:");
	String title=scanner.next().trim();
	print("text");
	String text=scanner.next().trim();
	questionManagementServive.update( id ,title,text);


}
	private void handleRemove() {
		print("Users ID:");
		int id = scanner.nextInt();
		userssmanagementservice.removeUserss(id);
	}

	private void print(String value) {
		System.out.println(value);
	}
	private void handleFilterTitle(){
		print("Introduce title to filter");
		String text=scanner.nextLine().trim();
		questionManagementServive.findtitle(text).forEach(s->print(s.toString()));
	};

	private void handleinsert(Question question, String tag){

		if("exit" == tag) return;
			if (tagManagementServive.findidtq(tag).isPresent()) { //daca tagul exista, il aribuim pe ala la intrebare
				qtagManagementServive.insert(question.getId(), tagManagementServive.findidtq(tag).get().getId());
				print("ok");
			} else {
				Tag tagNew = tagManagementServive.insert(tag);
				qtagManagementServive.insert(question.getId(), tagNew.getId());
				print("Oki");
			}


		}

}
