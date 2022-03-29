package bot.constant;

public class Const {
    public static final String GREETING_MESSAGE = "Я телеграмм-бот-уведомитель. Моя задача напоминать вам о событиях на неделе. У тебя может быть множество расписаний для твоего удобства, и в каждом расписании свои события.\n Подписываешься? \n  ";
    public static final String ACTION ="Выберите действие:\n";
    public static final String ACTION_EVENT ="Выбрано расписание: %s \n Выберите действие:\n";
    public static final String ACTION_TEMPLATE ="Ваш список расписаний:\n %s \n Выберите действие:\n";
    public static final String TEMPLATE_SELECT_ADD_EVENT ="\nВ каком расписании будем редактировать события? \n Отправьте номер.";
    public static final String SCHEDULE_MESSAGE ="";
    public static final String CREATION_ERROR="Ошибка создания пользователя. Попробуйте еще раз.";
    public static final String CREATION_ERROR_TEMPLATE="Ошибка при создании расписания! \n Такое название уже существет. Попробуйте еще раз.\n Выберите действие:";
    public static final String CREATION_TEMPLATE="Операция прошла успешно! \n Выберите действие:";
    public static final String INFO_BOT="Привет, %s! Для того чтоб начать работу, необходимо создать расписание. После в это расписания можно добавить новые собыития.";
    public static final String INFO_ADD_TEMPLATE="Как будет называться расписание? \n Отправьте название.";
    public static final String INFO_DELETE_TEMPLATE=" \n Какое расписание удалить? \n Отправьте номер.";
    public static final String INFO_SELECT_TEMPLATE=" \n У какого расписания вы хотите изменить активность? \n Отправьте номер.";
    public static final String INFO_SELECT_EVENT=" \n У какого события вы хотите изменить активность? \n Отправте номер.";
    public static final String INFO_DELETE_EVENT=" \n Какое событие вы хотите удалить? \n Отправьте номер.";

    public static final String INFO_ADD_EVENT_TEXT="Какое будет событие? \n Отправьте название.";
    public static final String INFO_ADD_EVENT_PERIOD="Повтор на каждой неделе? \n (Да/Нет)";
    public static final String INFO_ADD_EVENT_REPEATABLE="За сколько напомнить ? \n Отправте название.";
    public static final String INFO_ADD_EVENT_TIME="В какое время? \n Формат записи (ЧЧ:ММ).";
    public static final String INFO_ADD_EVENT_DATA="Какой день недели? \n";

    public static final String CREATION_ERROR_EVENT="Ошибка при создании события! \n Возможно вы использовали не тот формать записи.\n Выберите действие: \n\n Текущие расписание: %s \n";
    public static final String CREATION_EVENT="Операция прошла успешно! \n Выберите действие:\n\n Текущие расписание: %s \n ";
    public static final String ERROR_DELETE_EVENT="Ошибка при удалении! \n ";
    public static final String INFO_DONE_DELETE_EVENT="Операция прошла успешно! \n ";
}
