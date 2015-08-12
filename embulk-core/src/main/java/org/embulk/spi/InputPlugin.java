package org.embulk.spi;

import java.util.List;
import org.embulk.config.TaskSource;
import org.embulk.config.ConfigSource;
import org.embulk.config.ConfigDiff;
import org.embulk.config.CommitReport;

public interface InputPlugin
{
    interface Control
    {
        List<CommitReport> run(TaskSource taskSource,
                Schema schema, int taskCount);
    }

    ConfigDiff transaction(ConfigSource config,
            InputPlugin.Control control);

    ConfigDiff resume(TaskSource taskSource,
            Schema schema, int taskCount,
            InputPlugin.Control control);

    void cleanup(TaskSource taskSource,
            Schema schema, int taskCount,
            List<CommitReport> successCommitReports);

    CommitReport run(TaskSource taskSource,
            Schema schema, int taskIndex,
            PageOutput output);

    ConfigDiff guess(ConfigSource config);
}
