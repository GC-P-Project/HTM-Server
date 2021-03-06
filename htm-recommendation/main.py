# -*- coding: utf-8 -*-
"""P_Project_Recommendation_model.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1unFq-fKS7pFQCq8cbs3nvOMLZ4AJBTQ_
"""

from sklearn.decomposition import TruncatedSVD
from scipy.sparse.linalg import svds

import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
import numpy as np
import warnings

warnings.filterwarnings("ignore")

df_ratings = pd.read_csv('./HTM_data/video_ratings.csv')  #Read user-exercise video rating data, ratings.csv
                                                            # #사용자- 운동 영상 평점 데이터, ratings.csv 읽어오기
df_videos = pd.read_csv('./HTM_data/exercise_videos.csv')  # Read video(exercise video).csv
                                                            # #video(운동영상).csv 읽어오기

df_user_video_ratings = df_ratings.pivot(
    index='user_id',
    columns='exercise_id',
    values='score'
).fillna(0)  # User-exercise rating data, pivot conversion to apply to the model
            #사용자- 운동 영상 평점 데이터, 모델에 적용시키기 위해 pivot 변환

df_user_video_ratings.head()

# matrix is a pivot_table value made into a numpy matrix
#matrix는 pivot_table 값을 numpy matrix로 만든 것
matrix = df_user_video_ratings.values

# user_ratings_mean = Average rating data for the user's video
# #user_ratings_mean = 사용자의 영상에 대한 평균 평점 데이터
user_ratings_mean = np.mean(matrix, axis=1)

# matrix_user_mean: Data obtained by subtracting user average rating for user-exercise images
# matrix_user_mean : 사용자-운동영상에 대해 사용자 평균 평점을 뺀 데이터
matrix_user_mean = matrix - user_ratings_mean.reshape(-1, 1)

matrix

matrix.shape

user_ratings_mean.shape

matrix_user_mean.shape

pd.DataFrame(matrix_user_mean, columns=df_user_video_ratings.columns).head()

# svds() provided by scipy // scipy에서 제공해주는 svds().
# Returns U matrix, sigma matrix, V transpose matrix // 행렬, sigma행렬, V전치 행렬을 반환
U, sigma, Vt = svds(matrix_user_mean, k=20)  # 0<k<the number of users // 0<k<사용자수

print(U.shape)
print(sigma.shape)
print(Vt.shape)

sigma = np.diag(sigma)  # Convert to symmetric matrix containing zeros
                        # #0이 포함된 대칭 행렬로 변환

sigma

sigma.shape

sigma[0]

# If the dot product of U, Sigma, and Vt is performed, the original matrix is restored again.
# U, Sigma, Vt의 내적을 수행하면, 다시 원본 행렬로 복원이 된다.
# Apply + user average rating to the reconstructed matrix.
# 복원된 행렬에 + 사용자 평균 rating을 적용한다.
svd_user_predicted_ratings = np.dot(np.dot(U, sigma), Vt) + user_ratings_mean.reshape(-1, 1)

df_svd_preds = pd.DataFrame(svd_user_predicted_ratings, columns=df_user_video_ratings.columns)
df_svd_preds.head()

df_svd_preds.shape


def recommend_videos(df_svd_preds, user_id, ori_videos_df, ori_ratings_df,
                     num_recommendations=5):  # Reprocessed user-rating data, user_id, video data, rating data, number of recommended videosReprocessed user-rating data, user_id, video data, rating data, number of recommended videos
                                                # 재가공된 사용자-평점 데이터, user_id,영상데이터,평점데이터,추천영상갯수

    # Currently it is applied as an index, so user_id-1
    # 현재는 index로 적용이 되어있으므로 user_id - 1
    user_row_number = user_id - 1

    # 최종적으로 만든 pred_df에서 사용자 index에 따라 영화 데이터 정렬 -> 운동영상 평점이 높은 순으로 정렬
    # Sort movie data according to user index in the final created pred_df -> Sort in the order of highest athletic video rating
    sorted_user_predictions = df_svd_preds.iloc[user_row_number].sort_values(ascending=False)

    # 원본 평점 데이터에서 user id에 해당하는 데이터를 뽑아낸다.
    #Extract the data corresponding to the user ID from the original rating data.
    user_data = ori_ratings_df[ori_ratings_df.user_id == user_id]

    # 위에서 뽑은 user_data와 원래 운동영상 데이터를 합친다.
    # Add the user_data extracted above and the original exercise video data.
    user_history = user_data.merge(ori_videos_df, on='exercise_id').sort_values(['score'], ascending=False)

    # 원본 운동영상 데이터에서 사용자가 본 운동영상 데이터를 제외한 데이터를 추출
    # Extract data excluding the exercise image data viewed by the user from the original exercise image data
    recommendations = ori_videos_df[~ori_videos_df['exercise_id'].isin(user_history['exercise_id'])]

    # 사용자의 운동영상 평점이 높은 순으로 정렬된 데이터와 위 recommendations을 합친다.
    # Add the above recommendations with the data sorted in the order of the highest user's exercise image rating.
    recommendations = recommendations.merge(pd.DataFrame(sorted_user_predictions).reset_index(), on='exercise_id')

    # 컬럼 이름 바꾸고 정렬해서 return
    # Rename and sort the column and return
    recommendations = recommendations.rename(columns={user_row_number: 'Predictions'}).sort_values('Predictions',
                                                                                                   ascending=False).iloc[
                      :num_recommendations, :]

    return user_history, recommendations


print("운동영상 추천 함수 실행")
print("Execute exercise video recommendation function")
already_rated, predictions = recommend_videos(df_svd_preds, 25, df_videos, df_ratings,
                                              5)  # SVD후 재가공된 사용자-평점 데이터, user_id,영상데이터,평점데이터,추천영상갯수
                                                  # User-rating data, user_id, video data, rating data, number of recommended videos reprocessed after SVD
print("사용자가 이미 본 운동영상 리스트")
print("List of exercise videos the user has already seen")
print(already_rated)
already_rated.head()  # 사용자가 이미 본 운동영상 리스트
                      # List of exercise videos the user has already seen


print("사용자 맞춤 추천 운동영상 리스트")
print("Customized recommended exercise video list")
print(predictions)  # 사용자가 아직 보지 않은, 추천 운동영상 리스트
                    # List of recommended exercise videos that users have not yet seen