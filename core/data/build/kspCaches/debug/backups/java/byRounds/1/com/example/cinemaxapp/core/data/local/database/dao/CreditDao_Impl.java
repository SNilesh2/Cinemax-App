package com.example.cinemaxapp.core.data.local.database.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.EntityUpsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.cinemaxapp.core.data.local.database.entity.CreditEntity;
import com.example.cinemaxapp.core.data.local.database.entity.MovieCreditRef;
import com.example.cinemaxapp.core.data.local.database.entity.MovieEntity;
import com.example.cinemaxapp.core.data.local.database.entity.MovieWithCredits;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CreditDao_Impl implements CreditDao {
  private final RoomDatabase __db;

  private final SharedSQLiteStatement __preparedStmtOfClearCreditsForMovie;

  private final EntityUpsertionAdapter<CreditEntity> __upsertionAdapterOfCreditEntity;

  private final EntityUpsertionAdapter<MovieCreditRef> __upsertionAdapterOfMovieCreditRef;

  public CreditDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__preparedStmtOfClearCreditsForMovie = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM movie_credit_ref WHERE movie_id = ?";
        return _query;
      }
    };
    this.__upsertionAdapterOfCreditEntity = new EntityUpsertionAdapter<CreditEntity>(new EntityInsertionAdapter<CreditEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `credits` (`person_id`,`name`,`profile_path`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CreditEntity entity) {
        statement.bindLong(1, entity.getPersonId());
        statement.bindString(2, entity.getName());
        if (entity.getProfilePath() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getProfilePath());
        }
      }
    }, new EntityDeletionOrUpdateAdapter<CreditEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `credits` SET `person_id` = ?,`name` = ?,`profile_path` = ? WHERE `person_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CreditEntity entity) {
        statement.bindLong(1, entity.getPersonId());
        statement.bindString(2, entity.getName());
        if (entity.getProfilePath() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getProfilePath());
        }
        statement.bindLong(4, entity.getPersonId());
      }
    });
    this.__upsertionAdapterOfMovieCreditRef = new EntityUpsertionAdapter<MovieCreditRef>(new EntityInsertionAdapter<MovieCreditRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT INTO `movie_credit_ref` (`credit_id`,`movie_id`,`person_id`,`credit_type`,`character`,`job`,`department`,`cast_order`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieCreditRef entity) {
        statement.bindString(1, entity.getCreditId());
        statement.bindLong(2, entity.getMovieId());
        statement.bindLong(3, entity.getPersonId());
        statement.bindString(4, entity.getCreditType());
        if (entity.getCharacter() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCharacter());
        }
        if (entity.getJob() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getJob());
        }
        if (entity.getDepartment() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDepartment());
        }
        statement.bindLong(8, entity.getCastOrder());
      }
    }, new EntityDeletionOrUpdateAdapter<MovieCreditRef>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE `movie_credit_ref` SET `credit_id` = ?,`movie_id` = ?,`person_id` = ?,`credit_type` = ?,`character` = ?,`job` = ?,`department` = ?,`cast_order` = ? WHERE `credit_id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MovieCreditRef entity) {
        statement.bindString(1, entity.getCreditId());
        statement.bindLong(2, entity.getMovieId());
        statement.bindLong(3, entity.getPersonId());
        statement.bindString(4, entity.getCreditType());
        if (entity.getCharacter() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCharacter());
        }
        if (entity.getJob() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getJob());
        }
        if (entity.getDepartment() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDepartment());
        }
        statement.bindLong(8, entity.getCastOrder());
        statement.bindString(9, entity.getCreditId());
      }
    });
  }

  @Override
  public Object clearCreditsForMovie(final int movieId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearCreditsForMovie.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, movieId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearCreditsForMovie.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertAll(final List<CreditEntity> credits,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfCreditEntity.upsert(credits);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertMovieCreditRefs(final List<MovieCreditRef> refs,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __upsertionAdapterOfMovieCreditRef.upsert(refs);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MovieCreditRef>> getMovieCreditRefs(final int movieId) {
    final String _sql = "SELECT * FROM movie_credit_ref WHERE movie_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"movie_credit_ref"}, new Callable<List<MovieCreditRef>>() {
      @Override
      @NonNull
      public List<MovieCreditRef> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCreditId = CursorUtil.getColumnIndexOrThrow(_cursor, "credit_id");
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movie_id");
          final int _cursorIndexOfPersonId = CursorUtil.getColumnIndexOrThrow(_cursor, "person_id");
          final int _cursorIndexOfCreditType = CursorUtil.getColumnIndexOrThrow(_cursor, "credit_type");
          final int _cursorIndexOfCharacter = CursorUtil.getColumnIndexOrThrow(_cursor, "character");
          final int _cursorIndexOfJob = CursorUtil.getColumnIndexOrThrow(_cursor, "job");
          final int _cursorIndexOfDepartment = CursorUtil.getColumnIndexOrThrow(_cursor, "department");
          final int _cursorIndexOfCastOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "cast_order");
          final List<MovieCreditRef> _result = new ArrayList<MovieCreditRef>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MovieCreditRef _item;
            final String _tmpCreditId;
            _tmpCreditId = _cursor.getString(_cursorIndexOfCreditId);
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            final int _tmpPersonId;
            _tmpPersonId = _cursor.getInt(_cursorIndexOfPersonId);
            final String _tmpCreditType;
            _tmpCreditType = _cursor.getString(_cursorIndexOfCreditType);
            final String _tmpCharacter;
            if (_cursor.isNull(_cursorIndexOfCharacter)) {
              _tmpCharacter = null;
            } else {
              _tmpCharacter = _cursor.getString(_cursorIndexOfCharacter);
            }
            final String _tmpJob;
            if (_cursor.isNull(_cursorIndexOfJob)) {
              _tmpJob = null;
            } else {
              _tmpJob = _cursor.getString(_cursorIndexOfJob);
            }
            final String _tmpDepartment;
            if (_cursor.isNull(_cursorIndexOfDepartment)) {
              _tmpDepartment = null;
            } else {
              _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
            }
            final int _tmpCastOrder;
            _tmpCastOrder = _cursor.getInt(_cursorIndexOfCastOrder);
            _item = new MovieCreditRef(_tmpCreditId,_tmpMovieId,_tmpPersonId,_tmpCreditType,_tmpCharacter,_tmpJob,_tmpDepartment,_tmpCastOrder);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<MovieWithCredits> getMovieWithCredits(final int movieId) {
    final String _sql = "SELECT * FROM movies WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, movieId);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"movie_credit_ref", "credits",
        "movies"}, new Callable<MovieWithCredits>() {
      @Override
      @Nullable
      public MovieWithCredits call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfBackdropPath = CursorUtil.getColumnIndexOrThrow(_cursor, "backdrop_path");
            final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "poster_path");
            final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
            final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "vote_average");
            final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
            final int _cursorIndexOfRuntime = CursorUtil.getColumnIndexOrThrow(_cursor, "runtime");
            final int _cursorIndexOfTagline = CursorUtil.getColumnIndexOrThrow(_cursor, "tagline");
            final int _cursorIndexOfHomepage = CursorUtil.getColumnIndexOrThrow(_cursor, "homepage");
            final LongSparseArray<ArrayList<CreditEntity>> _collectionCredits = new LongSparseArray<ArrayList<CreditEntity>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionCredits.containsKey(_tmpKey)) {
                _collectionCredits.put(_tmpKey, new ArrayList<CreditEntity>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipcreditsAscomExampleCinemaxappCoreDataLocalDatabaseEntityCreditEntity(_collectionCredits);
            final MovieWithCredits _result;
            if (_cursor.moveToFirst()) {
              final MovieEntity _tmpMovie;
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              final String _tmpBackdropPath;
              if (_cursor.isNull(_cursorIndexOfBackdropPath)) {
                _tmpBackdropPath = null;
              } else {
                _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
              }
              final String _tmpPosterPath;
              if (_cursor.isNull(_cursorIndexOfPosterPath)) {
                _tmpPosterPath = null;
              } else {
                _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
              }
              final String _tmpReleaseDate;
              if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
                _tmpReleaseDate = null;
              } else {
                _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
              }
              final Double _tmpVoteAverage;
              if (_cursor.isNull(_cursorIndexOfVoteAverage)) {
                _tmpVoteAverage = null;
              } else {
                _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
              }
              final String _tmpOverview;
              if (_cursor.isNull(_cursorIndexOfOverview)) {
                _tmpOverview = null;
              } else {
                _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
              }
              final Integer _tmpRuntime;
              if (_cursor.isNull(_cursorIndexOfRuntime)) {
                _tmpRuntime = null;
              } else {
                _tmpRuntime = _cursor.getInt(_cursorIndexOfRuntime);
              }
              final String _tmpTagline;
              if (_cursor.isNull(_cursorIndexOfTagline)) {
                _tmpTagline = null;
              } else {
                _tmpTagline = _cursor.getString(_cursorIndexOfTagline);
              }
              final String _tmpHomepage;
              if (_cursor.isNull(_cursorIndexOfHomepage)) {
                _tmpHomepage = null;
              } else {
                _tmpHomepage = _cursor.getString(_cursorIndexOfHomepage);
              }
              _tmpMovie = new MovieEntity(_tmpId,_tmpTitle,_tmpBackdropPath,_tmpPosterPath,_tmpReleaseDate,_tmpVoteAverage,_tmpOverview,_tmpRuntime,_tmpTagline,_tmpHomepage);
              final ArrayList<CreditEntity> _tmpCreditsCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpCreditsCollection = _collectionCredits.get(_tmpKey_1);
              _result = new MovieWithCredits(_tmpMovie,_tmpCreditsCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipcreditsAscomExampleCinemaxappCoreDataLocalDatabaseEntityCreditEntity(
      @NonNull final LongSparseArray<ArrayList<CreditEntity>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshipcreditsAscomExampleCinemaxappCoreDataLocalDatabaseEntityCreditEntity(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `credits`.`person_id` AS `person_id`,`credits`.`name` AS `name`,`credits`.`profile_path` AS `profile_path`,_junction.`movie_id` FROM `movie_credit_ref` AS _junction INNER JOIN `credits` ON (_junction.`person_id` = `credits`.`person_id`) WHERE _junction.`movie_id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      // _junction.movie_id;
      final int _itemKeyIndex = 3;
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfPersonId = 0;
      final int _cursorIndexOfName = 1;
      final int _cursorIndexOfProfilePath = 2;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<CreditEntity> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final CreditEntity _item_1;
          final int _tmpPersonId;
          _tmpPersonId = _cursor.getInt(_cursorIndexOfPersonId);
          final String _tmpName;
          _tmpName = _cursor.getString(_cursorIndexOfName);
          final String _tmpProfilePath;
          if (_cursor.isNull(_cursorIndexOfProfilePath)) {
            _tmpProfilePath = null;
          } else {
            _tmpProfilePath = _cursor.getString(_cursorIndexOfProfilePath);
          }
          _item_1 = new CreditEntity(_tmpPersonId,_tmpName,_tmpProfilePath);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
