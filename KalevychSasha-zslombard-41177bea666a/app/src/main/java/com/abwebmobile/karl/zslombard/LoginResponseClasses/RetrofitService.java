package com.abwebmobile.karl.zslombard.LoginResponseClasses;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.abwebmobile.karl.zslombard.RoomEntities.Offer;
import com.abwebmobile.karl.zslombard.useCasesAndIteractors.Callbacker;

import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Karl on 15.02.2018.
 */

public class RetrofitService {
  Callbacker callbacker;


    public LiveData<List<Offer>> getOffers(String currentUserContractNumber, String currentUserLogin, String currentUserPassword, final Callbacker callbacker) {
        this.callbacker = callbacker;
        final MutableLiveData<List<Offer>> offers = new MutableLiveData<List<Offer>>();
        offers.setValue(new ArrayList<Offer>());
        final ArrayList<Offer> offerArrayList = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl() // Адрес сервера
                .client(new OkHttpClient())
                .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                        new Persister(new AnnotationStrategy() // important part!
                        )
                ))
                .build();

        OffersRequestServer service = retrofit.create(OffersRequestServer.class);



        Log.d("offersResponse", "params = 1)"+currentUserContractNumber+". 2)"+currentUserLogin+". 3)"+currentUserPassword+".");
        Call<Date> call = service.requestOffers("get_profile", currentUserContractNumber, currentUserLogin, currentUserPassword);
        call.enqueue(new Callback<Date>() {

            @Override
            public void onResponse(Call<Date> call, Response<Date> response) {
               Log.d("offersResponse", "all is ok");
               Log.d("offerResponse", response.body().toString());
              if (response.body().Rows!=null) {
                  for (int i = 0; i < response.body().Rows.rows.size(); i++) {
                      try {
                          Offer offer = new Offer();
                          try {
                              Log.d("offersResponse", "created offer");
                              Log.d("offersResponse", "accrued = " + response.body().Rows.rows.get(i).AccruedInterest.replaceAll("\\s+", ""));
                              offer.accuredInterests = Double.parseDouble(response.body().Rows.rows.get(i).AccruedInterest.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "accInterest setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.agreementDate = response.body().Rows.rows.get(i).AgreementDate.replaceAll("\\s+", "");
                              Log.d("offersResponse", "AgreementDate setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.contractNumber = Long.parseLong(response.body().Rows.rows.get(i).ContractNumber.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "ContractNumber setted");
                          } catch (Exception ignored) {
                          }

                          try {

                              offer.daysOverdue = Integer.parseInt(response.body().Rows.rows.get(i).DaysOverdue.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "DaysOverdue setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.daysTheEnd = Integer.parseInt(response.body().Rows.rows.get(i).DaysTheEnd.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "DaysTheEnd setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.description = response.body().Rows.rows.get(i).description;
                              Log.d("offersResponse", "description setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.expirationDate = response.body().Rows.rows.get(i).ExpirationDate.replaceAll("\\s+", "");
                              Log.d("offersResponse", "ExpirationDate setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.interestRatePerDay = Double.parseDouble(response.body().Rows.rows.get(i).InterestRatePerDay.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "InterestRatePerDay setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.lastOperationDate = response.body().Rows.rows.get(i).LastOperationDate.replaceAll("\\s+", "");
                              Log.d("offersResponse", "LastOperationDate setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.matching = Double.parseDouble(response.body().Rows.rows.get(i).Dodor.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "Dodor setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.maximumAmountPayment = Double.parseDouble(response.body().Rows.rows.get(i).MaximumAmountPayment.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "MaximumAmountPayment setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.minimumAmountPayment = Double.parseDouble(response.body().Rows.rows.get(i).MinimumAmountPayment.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "MinimumAmountPayment setted");
                          } catch (Exception ignored) {
                          }
                          try {
                              offer.maximumCredit = Double.parseDouble(response.body().Rows.rows.get(i).MaximumCredit.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "MaximumCredit setted");
                          } catch (Exception e) {
                          }
                          try {
                              offer.surcharge = Double.parseDouble(response.body().Rows.rows.get(i).Surcharge.replaceAll("\\s+", ""));
                          } catch (Exception ignored) {
                          }
                          try {
                              Log.d("offersResponse", "Coredit bal = " + response.body().Rows.rows.get(i).CreditBalance.replaceAll("\\s+", ""));
                              offer.creditBalance = Double.parseDouble(response.body().Rows.rows.get(i).CreditBalance.replaceAll("\\s+", ""));
                              Log.d("offersResponse", "CreditBalance setted");
                          } catch (Exception e) {

                          }
                          offerArrayList.add(offer);
                          Log.d("offersResponse", " ADDED TO LIST");
                      } catch (Exception e) {
                      } finally {
                          // callbacker.onCallback("stopLoading");
                      }


                  }
              }

              offers.setValue(offerArrayList);
                Log.d("offersResponse", " setted list size = "+offerArrayList.size());
            }

            @Override
            public void onFailure(Call<Date> call, Throwable t) {
                Log.d("offersResponse", "onFailure");
                Log.d("offersResponse", t.getMessage());
                callbacker.onCallback("stopLoading");
            }

        });

        return offers;

    }

}
