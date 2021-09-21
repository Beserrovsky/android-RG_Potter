package com.beserrovsky.rgpotter.data.user;

import android.util.Base64;

import com.beserrovsky.rgpotter.data.RepositoryCallback;
import com.beserrovsky.rgpotter.data.Result;
import com.beserrovsky.rgpotter.models.UserModel;
import com.beserrovsky.rgpotter.ui.rg.LoginViewModel;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;

public class UserRepository {

    private final String userUrl = "https://rgpotterapi.herokuapp.com/api/user";

    private final UserParser userParser;
    private final Executor executor;
    private final LoginViewModel userViewModel;

    public UserRepository (UserParser parser, LoginViewModel userViewModel, Executor executor) {
        this.userParser = parser;
        this.executor = executor;
        this.userViewModel = userViewModel;
    }

    public void makeGetRequest(final RepositoryCallback<UserModel> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Result<UserModel> result = makeSynchronousGetRequest();
                    callback.onComplete(result);
                } catch (Exception e) {
                    Result<UserModel> errorResult = new Result.Error<>(e);
                    callback.onComplete(errorResult);
                }
            }
        });
    }

    public void makePostRequest(final String jsonBody, final RepositoryCallback<UserModel> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Result<UserModel> result = makeSynchronousPostRequest(jsonBody);
                    callback.onComplete(result);
                } catch (Exception e) {
                    Result<UserModel> errorResult = new Result.Error<>(e);
                    callback.onComplete(errorResult);
                }
            }
        });
    }

    public void makePatchRequest(final String jsonBody, final RepositoryCallback<UserModel> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Result<UserModel> result = makeSynchronousPostRequest(jsonBody);
                    callback.onComplete(result);
                } catch (Exception e) {
                    Result<UserModel> errorResult = new Result.Error<>(e);
                    callback.onComplete(errorResult);
                }
            }
        });
    }

    public void makeDeleteRequest(final String jsonBody, final RepositoryCallback<UserModel> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Result<UserModel> result = makeSynchronousPostRequest(jsonBody);
                    callback.onComplete(result);
                } catch (Exception e) {
                    Result<UserModel> errorResult = new Result.Error<>(e);
                    callback.onComplete(errorResult);
                }
            }
        });
    }

    private Result<UserModel> makeSynchronousGetRequest() {
        try {
            URL url = new URL(userUrl);

            String Auth = ("Bearer " + userViewModel.getJWT().getValue()).toString().trim();

            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Authorization", Auth);
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");

            UserModel postResponse = new UserParser().parse(httpConnection.getInputStream());
            return new Result.Success<UserModel>(postResponse);
        } catch (Exception e) {
            return new Result.Error<UserModel>(e);
        }
    }

    private Result<UserModel> makeSynchronousPostRequest(String jsonBody) {
        try {
            URL url = new URL(userUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Authorization","Bearer " + userViewModel.getJWT());
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setDoOutput(true);
            httpConnection.getOutputStream().write(jsonBody.getBytes("utf-8"));

            UserModel postResponse = new UserParser().parse(httpConnection.getInputStream());
            return new Result.Success<UserModel>(postResponse);
        } catch (Exception e) {
            return new Result.Error<UserModel>(e);
        }
    }

    private Result<UserModel> makeSynchronousPatchRequest(String jsonBody) {
        try {
            URL url = new URL(userUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("PATCH");
            httpConnection.setRequestProperty("Authorization","Bearer " + userViewModel.getJWT());
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setDoOutput(true);
            httpConnection.getOutputStream().write(jsonBody.getBytes("utf-8"));

            UserModel postResponse = new UserParser().parse(httpConnection.getInputStream());
            return new Result.Success<UserModel>(postResponse);
        } catch (Exception e) {
            return new Result.Error<UserModel>(e);
        }
    }

    private Result<UserModel> makeSynchronousDeleteRequest(String jsonBody) {
        try {
            URL url = new URL(userUrl);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("DELETE");
            httpConnection.setRequestProperty("Authorization","Bearer " + userViewModel.getJWT());
            httpConnection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            httpConnection.setRequestProperty("Accept", "application/json");
            httpConnection.setDoOutput(true);
            httpConnection.getOutputStream().write(jsonBody.getBytes("utf-8"));

            UserModel postResponse = new UserParser().parse(httpConnection.getInputStream());
            return new Result.Success<UserModel>(postResponse);
        } catch (Exception e) {
            return new Result.Error<UserModel>(e);
        }
    }
}
