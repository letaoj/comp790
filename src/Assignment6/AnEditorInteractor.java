package Assignment6;

import trace.echo.ListEditDisplayed;
import trace.echo.ListEditInput;
import trace.echo.modular.ListEditObserved;
import trace.echo.modular.OperationName;
import util.annotations.Tags;
import util.session.Communicator;
import util.tags.ApplicationTags;
import util.tags.InteractiveTags;
import echo.modular.ListObserver;
import echo.modular.SimpleList;

@Tags({ApplicationTags.EDITOR, InteractiveTags.INTERACTOR})
public class AnEditorInteractor implements ListObserver {

  private boolean selfDisplay = true;

  protected SimpleList list;
  protected Communicator communicator;
  protected UI ui;
  protected boolean callback = true;

  public AnEditorInteractor(SimpleList list) {
    this.list = list;
  }

  protected void addToList(int index, Character newValue) {
    if (callback) {
      ((ReplicatedSimpleList) list).replicatedAdd(index, newValue);
    } else {
      ((ReplicatedSimpleList) list).add(index, newValue);
      callback = true;
    }
  }

  protected void removeFromList(int index, Character oldValue) {
    if (callback) {
      ((ReplicatedSimpleList) list).replicatedRemove(index, oldValue);
    } else {
      ((ReplicatedSimpleList) list).remove(index);
      callback = true;
    }
  }

  protected void processAddTopic(int index, String input) {
    ListEditInput.newCase(OperationName.ADD, list.size(), input, ApplicationTags.EDITOR, this);
    selfDisplay = false;
    for (int i = 0; i < input.length(); i++) {
      addToList(index + i, input.charAt(i));
    }
    selfDisplay = true;
  }

  protected void processRemoveTopic(int index, String input) {
    ListEditInput.newCase(OperationName.DELETE, list.size(), input, ApplicationTags.EDITOR, this);
    selfDisplay = false;
    for (int i = 0; i < input.length(); i++) {
      removeFromList(index, input.charAt(i));
    }
    selfDisplay = true;
  }

  @Override
  public void elementAdded(int anIndex, Object aNewValue) {
    ListEditObserved.newCase(OperationName.ADD, anIndex, aNewValue, ApplicationTags.EDITOR, this);
    if (selfDisplay) {
      callback = false;
      displayAdd(anIndex, (Character) list.get(anIndex));
    }
    ListEditDisplayed.newCase(OperationName.ADD, anIndex, aNewValue, ApplicationTags.EDITOR, this);
  }

  public void setUI(UI ui) {
    this.ui = ui;
  }

  public void setCommunicator(Communicator communicator) {
    this.communicator = communicator;
  }

  protected void displayAdd(int index, Character newValue) {
    ui.addTopic(index, newValue);
  }

  protected void displayRemove(int index, Character newValue) {
    ui.removeTopic(index);
  }

  @Override
  public void elementRemoved(int anIndex, Object aNewValue) {
    ListEditObserved
        .newCase(OperationName.DELETE, anIndex, aNewValue, ApplicationTags.EDITOR, this);
    if (selfDisplay) {
      callback = false;
      displayRemove(anIndex, (Character) list.get(anIndex));
    }
    ListEditDisplayed.newCase(OperationName.DELETE, anIndex, aNewValue, ApplicationTags.EDITOR,
        this);
  }

}
