<2020/12/16>
사용법- 실제 사용시 python 라이브러리를 서버에 설치해야 합니다.

<필요 라이브러리>
sklearn
scipy
matplotlib
seaborn
pandas
numpy


<데이터 불러오는 부분 오류시>
csv파일을 불러오는게 오류시 수정해야합니다.

지금 현재 파이썬 모듈을 기준으로 다음과 같이 정의되어있습니다.

df_ratings  = pd.read_csv('./HTM_data/video_ratings.csv') # 사용자- 운동 영상 평점 데이터, ratings.csv 읽어오기
df_videos  = pd.read_csv('./HTM_data/exercise_videos.csv')   # video(운동영상).csv 읽어오기


오류가 날 시
전체 앱을 시작하는 서버 소스파일을 기준으로 주소를 상대주소나 , 아예 절대주소로 수정해야합니다.