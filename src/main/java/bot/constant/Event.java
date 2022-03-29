package bot.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Event {
      SUBSCRIBE ("Подписаться"),
      START_PLANING ("Выбрать расписание"),
      START_WORK ("Перейти к событиям на этой неделе"),

      SELECT_TIMETABLE_ADD_EVENT ( "Выбрать расписания для редактирования событий"),
      ADD_EVENT ( "Добавить событие"),
      ADD_EVENT_TEXT ( "Добавить текст в новое событие"),
      ADD_EVENT_REPEATABLE ( "Добавить повтор события"),
      ADD_EVENT_TIME ( "Добавить время события"),
      ADD_EVENT_DATA ( "Добавить двту события"),

      SHOW_EVENT ( "Показать события на неделе"),
      SELECT_EVENT ( "Изменить статус события"),
      DELETE_EVENT ( "Удалить собыитие"),

      ADD_TIMETABLE( "Добавить расписание"),
      DELETE_TEMPLATE( "Удалить расписание"),
      SELECT_TIMETABLE ( "Изменить активность расписаний"),
      BACK ("Вернуться назад"),

      CANCEL_TEMPLATE ("Отмена"),
      CANCEL_EVENT ("Отмена записи"),
      BACK_TEMPLATE ("Назад "),
      ADD_NEW_TEMPLATE  ("Добавить"),
      NOD_FOUND ("Поле не найдено"),

      YES("Да"),
      NOT("Нет"),

      MONDAY("Пн"),
      TUESDAY("Вт"),
      WEDNESDAY("Ср"),
      THURSDAY("Чт"),
      FRIDAY("Пт"),
      SATURDAY("Сб"),
      SUNDAY("Вс");




      private Event event;
      private final String title;

    public static Event of(String s){
          return Arrays.stream(values()).filter(e -> (e.title.equals(s))).findAny().orElse(NOD_FOUND);
    }

      public Event getEvent() {
            return event;
      }

      public void setEvent(Event event) {
            this.event = event;
      }
}
