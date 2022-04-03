package bot.constant;

public class Const {
    public static final String GREETING_MESSAGE = "Я Telegram бот-уведомитель. Моя задача - напоминать тебе о событиях на неделе. Ты можешь сделать множество расписаний (для работы, учёбы, спорта), и в каждом расписании создать свои события.\n Подписываешься? \n  ";
    public static final String ACTION ="Выбери действие:\n";
    public static final String ACTION_EVENT ="Выбрано расписание: %s \n Выбери действие:\n";
    public static final String ACTION_TEMPLATE ="Список расписаний:\n %s \n Выбери действие:\n";
    public static final String TEMPLATE_SELECT_ADD_EVENT ="\nВ каком расписании будем редактировать события? \n Отправь номер.";
    public static final String SCHEDULE_MESSAGE ="";
    public static final String CREATION_ERROR="Ошибка создания пользователя. Попробуй ещё раз.";
    public static final String CREATION_ERROR_TEMPLATE="Ошибка при создании расписания. \n Такое название уже существует. Попробуй ещё раз.\n Выбери действие:";
    public static final String CREATION_TEMPLATE="Операция прошла успешно! \n Выбери действие:";
    public static final String INFO_BOT="Привет, %s! Чтобы начать работу, необходимо создать расписание.";
    public static final String INFO_ADD_TEMPLATE="Как будет называться расписание? \n Отправь название.";
    public static final String INFO_DELETE_TEMPLATE=" \n Какое расписание удалить? \n Отправь номер.";
    public static final String INFO_SELECT_TEMPLATE=" \n У какого расписания изменить активность? \n Отправь номер.";
    public static final String INFO_SELECT_EVENT=" \n У какого события изменить активность? \n Отправь номер.";
    public static final String INFO_DELETE_EVENT=" \n Какое событие удалить? \n Отправь номер.";

    public static final String INFO_ADD_EVENT_TEXT="Какое событие добавить? \n Отправь название.";
    public static final String INFO_ADD_EVENT_PERIOD="Повтор на каждой неделе? \n (Да/Нет)";
    public static final String INFO_ADD_EVENT_REPEATABLE="За сколько напомнить ? \n Отправь название.";
    public static final String INFO_ADD_EVENT_TIME="В какое время? \n Формат записи (ЧЧ:ММ).";
    public static final String INFO_ADD_EVENT_DATA="Какой день недели? \n";

    public static final String CREATION_ERROR_EVENT="Ошибка при создании события. \n Возможно, запись имеет неверный формат.\n Выбери действие: \n\n Текущее расписание: %s \n";
    public static final String CREATION_EVENT="Операция прошла успешно! \n Текущее расписание: %s \n ";
    public static final String ERROR_DELETE_EVENT="Ошибка при удалении. \n ";
    public static final String INFO_DONE_DELETE_EVENT="Операция прошла успешно! \n ";
}
