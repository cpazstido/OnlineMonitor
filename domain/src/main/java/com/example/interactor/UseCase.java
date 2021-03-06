package com.example.interactor;

import com.example.executor.PostExecutionThread;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a {@link Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase {

  private final PostExecutionThread postExecutionThread;
  private final Scheduler subExecutionThread;

  private Subscription subscription = Subscriptions.empty();

  protected UseCase(PostExecutionThread postExecutionThread ,Scheduler subExecutionThread) {
    this.postExecutionThread = postExecutionThread;
    this.subExecutionThread = subExecutionThread;
  }

  /**
   * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
   */
  protected abstract Observable buildUseCaseObservable();

  /**
   * Executes the current use case.
   *
   * @param UseCaseSubscriber The guy who will be listen to the observable build with {@link #buildUseCaseObservable()}.
   */
  @SuppressWarnings("unchecked")
  public void execute(Subscriber UseCaseSubscriber) {
    this.subscription = this.buildUseCaseObservable()
//            .subscribeOn(Schedulers.io())
            .subscribeOn(subExecutionThread)
            .observeOn(postExecutionThread.getScheduler())
            .subscribe(UseCaseSubscriber);
  }

  /**
   * Unsubscribes from current {@link Subscription}.
   */
  public void unsubscribe() {
    if (!subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }

}
