package ro.utcn.spet.example.a1.repository;



public interface RepositoryFactory {

	UserssRepository createUserssRepository();
	QuestionRepository createQuestionRepository();
	TagRepository createTagRepository();
	QtagRepository createQtagRepository();



}
